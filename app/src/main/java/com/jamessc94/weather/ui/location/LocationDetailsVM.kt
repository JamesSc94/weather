package com.jamessc94.weather.ui.location

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jamessc94.weather.model.WLocationAdap
import com.jamessc94.weather.model.WSavedLocation
import com.jamessc94.weather.model.asDatabaseModel
import com.jamessc94.weather.model.asDatabaseSavedModel
import com.jamessc94.weather.persistence.WLocationDAO
import com.jamessc94.weather.persistence.WSavedLocationDAO
import com.jamessc94.weather.repo.LocationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsVM @Inject constructor(
    private val dao: WLocationDAO,
    private val daoS: WSavedLocationDAO,
    private val locationRepo: LocationRepo,
    private val savedStateHandle: SavedStateHandle,
    application: Application
): AndroidViewModel(application) {

    var isLoading = MutableLiveData<Boolean>(false)

    var toastMessage = MutableLiveData<String>("")

//    val wlocations = dao.getWLocation(savedStateHandle.get<String>(LocationDetailsFrag.LTYPE_KEY)!!)
    val wlocations = dao.getWLocationWS(savedStateHandle.get<String>(LocationDetailsFrag.LTYPE_KEY)!!)

    init {
        viewModelScope.launch {
            try{
                val response = locationRepo.fetchLocationList(
                    onStart = { isLoading.value = true },
                    onComplete = { isLoading.value = false },
                    onError = { toastMessage.value = it
                        isLoading.value = false},
                    savedStateHandle.get<String>(LocationDetailsFrag.LTYPE_KEY)!!
                )

                if(response.isSuccessful){
                    dao.insertWLocationList(*response.body()!!.asDatabaseModel(savedStateHandle.get<String>(LocationDetailsFrag.LTYPE_KEY)!!))

                }

            }catch (e : Exception){
                isLoading.value = false
                e.printStackTrace()

            }

        }

    }

    fun updateSavedLocation(model: WLocationAdap, delete: Boolean){
        viewModelScope.launch {
            if(delete){
                daoS.deleteWSavedLocation(model.id)

            }else{
                daoS.insertWSavedLocationList(model.asDatabaseSavedModel())

            }

        }

    }

}