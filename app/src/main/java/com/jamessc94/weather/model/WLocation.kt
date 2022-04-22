package com.jamessc94.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "wlocation")
data class WLocation(

    @PrimaryKey
    @ColumnInfo(name= "id")
    var id: String = "",

    @ColumnInfo(name= "name")
    var name: String = "",

    @ColumnInfo(name= "latitude")
    var latitude: String? = "",

    @ColumnInfo(name= "longitude")
    var longitude: String? = "",

    @ColumnInfo(name= "type")
    var type: String = "",

)

data class WLocationAdap(

    var id: String = "",
    var name: String = "",
    var latitude: String? = "",
    var longitude: String? = "",
    var type: String = "",
    var locationid: String? = "",

)

@JsonClass(generateAdapter = true)
data class WLocationListContainer(val results : List<WLocation>)

fun WLocationListContainer.asDatabaseModel(type: String): Array<WLocation> {
    return results.map {
        WLocation(
            id = it.id,
            name = it.name,
            latitude = it.latitude,
            longitude = it.longitude,
            type = type
        )
    }.toTypedArray()

}

fun WLocationAdap.asDatabaseSavedModel(): WSavedLocation {
    return WSavedLocation(
        locationid = this.id,
        name = this.name

    )

}