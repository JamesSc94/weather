package com.jamessc94.weather.ui.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jamessc94.weather.model.asDatabaseModel
import com.jamessc94.weather.persistence.WLocationDAO
import com.jamessc94.weather.repo.HomeRepo
import com.jamessc94.weather.repo.LocationRepo
import com.jamessc94.weather.utils.WeatherTypeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationVM @Inject constructor(
    private val dao: WLocationDAO,
    private val locRepo: LocationRepo,
    application: Application
) : AndroidViewModel(application) {

}