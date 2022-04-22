package com.jamessc94.weather.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jamessc94.weather.model.WLocation
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object Network {
    var client = OkHttpClient.Builder()
        .addInterceptor(ServiceInterceptor())
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.met.gov.my/v2/")
//        .baseUrl("https://devbytes.udacity.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val retrofit21 = Retrofit.Builder()
        .baseUrl("https://api.met.gov.my/v2.1/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retroWeather : WeatherService = retrofit.create(WeatherService::class.java)
    val retroWLocation : WLocationService = retrofit21.create(WLocationService::class.java)
    val retroWDatatype : WDatatypeService = retrofit21.create(WDatatypeService::class.java)

}