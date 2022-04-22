package com.jamessc94.weather.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamessc94.weather.model.WLocation
import com.jamessc94.weather.model.WLocationAdap

@Dao
interface WLocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWLocationList(vararg updates : WLocation)

    @Query("SELECT * FROM wlocation WHERE type=:type")
    fun getWLocation(type: String): LiveData<List<WLocation>>

    @Query("SELECT wl.*, ws.locationid FROM wlocation AS wl LEFT JOIN wsavedlocation AS ws ON wl.id = ws.locationid WHERE wl.type=:type")
    fun getWLocationWS(type: String): LiveData<List<WLocationAdap>>

    @Query("SELECT * FROM wlocation")
    fun getWLocationAll(): LiveData<List<WLocation>>

}