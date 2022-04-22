package com.jamessc94.weather.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamessc94.weather.model.WDatatype

@Dao
interface WDatatypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWDatatypeList(vararg updates : WDatatype)

    @Query("SELECT * FROM wdatatype WHERE id=:id")
    fun getWDatatype(id: String): LiveData<List<WDatatype>>

    @Query("SELECT * FROM wdatatype")
    fun getWDatatypeAll(): LiveData<List<WDatatype>>

}