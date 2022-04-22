package com.jamessc94.weather.di

import android.content.Context
import com.jamessc94.weather.persistence.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context : Context) = AppDatabase.getIntance(context)

    @Provides
    fun provideWeatherDAO(appDatabase: AppDatabase): WeatherDAO {
        return appDatabase.weatherDAO()
    }

    @Provides
    fun provideWDatatypeDAO(appDatabase: AppDatabase): WDatatypeDAO{
        return appDatabase.wdatatypeDAO()
    }

    @Provides
    fun provideWSavedLocationDAO(appDatabase: AppDatabase): WSavedLocationDAO{
        return appDatabase.wsavedlocationDAO()
    }

    @Provides
    fun provideLocationDAO(appDatabase: AppDatabase): WLocationDAO {
        return appDatabase.wlocationDAO()
    }

}