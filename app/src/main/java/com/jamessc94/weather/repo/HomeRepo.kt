package com.jamessc94.weather.repo

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.model.WeatherListContainer
import com.jamessc94.weather.model.asDatabaseModel
import com.jamessc94.weather.network.Network
import com.jamessc94.weather.persistence.AppDatabase
import com.jamessc94.weather.persistence.WeatherDAO
import com.jamessc94.weather.ui.home.HomeFrag
import com.jamessc94.weather.ui.home.HomeVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class HomeRepo @Inject constructor() {

    suspend fun fetchWeatherList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        lid: String,
        date: String,

    ) : Response<WeatherListContainer> {

        onStart()

        Network.retroWeather.fetchWeatherList("FORECAST", "GENERAL",
            lid, date, date).apply {
                if(this.isSuccessful){
                    onComplete()

                }else{
                    if(this.errorBody()!!.string().contains("invalid arguments", ignoreCase = true)){
                        onError("No Data Available")

                    }else{
                        onError(this.errorBody()!!.string())

                    }

                }

            return this

        }

    }

}
