package com.jamessc94.weather.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jamessc94.weather.model.WDatatype
import com.jamessc94.weather.model.WSavedLocation

@Dao
interface WSavedLocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWSavedLocationList(updates : WSavedLocation)

    @Query("SELECT * FROM wsavedlocation WHERE locationid=:locationid")
    fun getWSavedLocation(locationid: String): LiveData<List<WSavedLocation>>

    @Query("SELECT * FROM wsavedlocation")
    fun getWSavedLocationAll(): LiveData<List<WSavedLocation>>

    @Query("DELETE FROM wsavedlocation WHERE locationid=:locationid")
    suspend fun deleteWSavedLocation(locationid : String)

}