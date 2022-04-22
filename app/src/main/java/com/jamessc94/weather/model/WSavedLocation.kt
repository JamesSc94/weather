package com.jamessc94.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wsavedlocation")
data class WSavedLocation(

    @PrimaryKey
    @ColumnInfo(name = "locationid")
    var locationid: String,

    @ColumnInfo(name = "name")
    var name: String,

)