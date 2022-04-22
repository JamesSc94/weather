package com.jamessc94.weather.network

import com.jamessc94.weather.model.WeatherListContainer
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data")
    suspend fun fetchWeatherList(@Query("datasetid") datasetid: String,
                         @Query("datacategoryid") datacategoryid: String,
                         @Query("locationid") locationid: String,
                         @Query("start_date") start_date: String,
                         @Query("end_date") end_date: String): Response<WeatherListContainer>

    @GET("devbytes.json")
    fun getUpdateList(): Call<WeatherListContainer>

}