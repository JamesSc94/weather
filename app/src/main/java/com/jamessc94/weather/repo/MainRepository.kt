package com.jamessc94.weather.repo

import androidx.annotation.WorkerThread
import com.jamessc94.weather.network.Network
import com.jamessc94.weather.persistence.WeatherDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val weatherDAO: WeatherDAO,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

//    @WorkerThread
//    fun fetchWeatherList(
//        onStart: () -> Unit,
//        onComplete: () -> Unit,
//        onError: (String?) -> Unit
//    ) = flow<Weather> {
//        val weathers = weatherDAO.getWeatherAll()
//
//        if(weathers.videos.isEmpty()){
//            val response = Network.GetWeatherAll.fetchWeatherList().await()
//
//            weatherDAO.insertWeatherList(response)
//            emit(weatherDAO.getWeatherAll())
//
//        }else{
//            emit(weathers)
//
//        }
//
//    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}