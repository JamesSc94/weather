package com.jamessc94.weather.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jamessc94.weather.model.WSavedLocation
import com.jamessc94.weather.model.asDatabaseModel
import com.jamessc94.weather.persistence.WDatatypeDAO
import com.jamessc94.weather.persistence.WSavedLocationDAO
import com.jamessc94.weather.repo.DashRepo
import com.jamessc94.weather.ui.adapter.AdapDash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashVM @Inject constructor(
    private val dao: WDatatypeDAO,
    private val daoS: WSavedLocationDAO,
    private val repo: DashRepo,
    application: Application
) : AndroidViewModel(application) {

    var isLoading = MutableLiveData<Boolean>(false)
    var toastMessage = MutableLiveData<String>("")

    val wdatatypes = dao.getWDatatypeAll()
    val wsavedlocations = daoS.getWSavedLocationAll()
    val wsavedlocationst = daoS.getWSavedLocationAll()

    init {
        viewModelScope.launch {
            try{
                val response = repo.getWDatatype(
                    onStart = { isLoading.value = true },
                    onComplete = { isLoading.value = false },
                    onError = { toastMessage.value = it
                        isLoading.value = false},
                )

                if(response.isSuccessful){
                    dao.insertWDatatypeList(*response.body()!!.asDatabaseModel())

                }

            }catch (e: Exception){
                e.printStackTrace()

            }

        }

    }

    fun addTest(){
        viewModelScope.launch {
//            daoS.insertWSavedLocationList(WSavedLocation("2"))
        }
    }

}