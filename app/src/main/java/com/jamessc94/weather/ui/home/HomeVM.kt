package com.jamessc94.weather.ui.home

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.jamessc94.weather.model.*
import com.jamessc94.weather.network.Network
import com.jamessc94.weather.persistence.WSavedLocationDAO
import com.jamessc94.weather.persistence.WeatherDAO
import com.jamessc94.weather.repo.HomeRepo
import com.jamessc94.weather.ui.home.HomeFrag.Companion.LID_KEY
import com.jamessc94.weather.ui.home.HomeFrag.Companion.LNAME_KEY
import com.jamessc94.weather.utils.DateUtils.currentDate
import com.jamessc94.weather.utils.DateUtils.getDateDay
import com.jamessc94.weather.utils.DateUtils.updateDays
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val dao: WeatherDAO,
    private val daoS: WSavedLocationDAO,
    private val homeRepo: HomeRepo,
    private val savedStateHandle: SavedStateHandle,
    application: Application
): AndroidViewModel(application) {

    var isLoading = MutableLiveData<Boolean>(false)
    val liveClearData = MutableLiveData<Boolean>(false)
    var toastMessage = MutableLiveData<String>("")
    val liveName = MutableLiveData<String>(savedStateHandle.get<String>(LNAME_KEY)!!)
    val liveDate = MutableLiveData<String>(Date().currentDate())
    val liveDayOfWeek = MutableLiveData<String>(Date().getDateDay())

    val weathers = dao.getWeather(savedStateHandle.get<String>(LID_KEY)!!)

    val weathersByDate = dao.getWeatherByPage(savedStateHandle.get<String>(LID_KEY)!!, liveDate.value!!)

    init {
        updateWeatherDB()

    }

    fun updateWeatherDB(){
        viewModelScope.launch {
            try{
                val response = homeRepo.fetchWeatherList(
                    onStart = { isLoading.value = true },
                    onComplete = { isLoading.value = false },
                    onError = {
                        toastMessage.value = it
                        isLoading.value = false
                        liveClearData.value = it.equals("No Data Available", ignoreCase = true) },
                    lid = savedStateHandle.get<String>(LID_KEY)!!,
                    date = liveDate.value!!,
                )

                if(response.isSuccessful){
                    dao.insertWeatherList(*response.body()!!.asDatabaseModel())

                }

            }catch (e : Exception){
                isLoading.value = false

                e.printStackTrace()

            }

        }

    }

    fun getWeatherBy(locationid: String) : LiveData<List<Weather>>{
        return dao.getWeather(locationid)

    }

    fun updateSavedLocation(){
        viewModelScope.launch {
            daoS.deleteWSavedLocation(savedStateHandle.get<String>(LID_KEY)!!)

        }

    }

    fun updateDate(days: Int){
        liveDate.value!!.updateDays(days).apply {
            liveDate.value = this.currentDate()
            liveDayOfWeek.value = this.getDateDay()

            updateWeatherDB()

        }

    }

}