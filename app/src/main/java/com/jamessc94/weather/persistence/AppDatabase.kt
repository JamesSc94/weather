package com.jamessc94.weather.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jamessc94.weather.model.WDatatype
import com.jamessc94.weather.model.WLocation
import com.jamessc94.weather.model.WSavedLocation
import com.jamessc94.weather.model.Weather

@Database(entities = [WDatatype::class, WSavedLocation::class,
    WLocation::class, Weather::class], version = 15, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wdatatypeDAO(): WDatatypeDAO
    abstract fun wsavedlocationDAO(): WSavedLocationDAO
    abstract fun wlocationDAO(): WLocationDAO
    abstract fun weatherDAO(): WeatherDAO

    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getIntance(context: Context) : AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "weather_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance

                }

                return instance

            }

        }

    }

}