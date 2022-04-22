package com.jamessc94.weather.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.jamessc94.weather.model.Weather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherList(vararg updates : Weather)

    @Query("SELECT * FROM Weather WHERE locationid = :locationid")
    fun getWeather(locationid: String): LiveData<List<Weather>>

    @Query("SELECT * FROM Weather WHERE locationid = :locationid AND date = :date")
    fun getWeatherByPage(locationid: String, date: String): LiveData<List<Weather>>

    @Query("SELECT * FROM Weather")
    fun getWeatherAll() : LiveData<List<Weather>>

}