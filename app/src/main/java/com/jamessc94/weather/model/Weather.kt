package com.jamessc94.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jamessc94.weather.utils.DateUtils.toDate
import com.jamessc94.weather.utils.WeatherTypeUtils
import com.jamessc94.weather.utils.WeatherTypeUtils.getTypeWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "weather", primaryKeys = ["locationid", "date", "datatype"])
@JsonClass(generateAdapter = true)
data class Weather(

    @ColumnInfo(name = "locationid")
    var locationid:String = "",

    @ColumnInfo(name = "locationname")
    var locationname:String = "",

    @ColumnInfo(name = "date")
    var date:String = "",

    @ColumnInfo(name = "datatype")
    var datatype:String = "",

    @ColumnInfo(name = "value")
    var value:String = "",

    @ColumnInfo(name = "latitude")
    var latitude:String = "",

    @ColumnInfo(name = "longitude")
    var longitude:String = "",

)

data class WeatherRead(
    var date:String = "",
    var type:String = "",
    var value:String = "",

)

@JsonClass(generateAdapter = true)
data class WeatherListContainer(val results : List<Weather>)

fun WeatherListContainer.asDatabaseModel(): Array<Weather> {
    return results.map {
        Weather(
            locationid = it.locationid,
            locationname = it.locationname,
            date = it.date.toDate(),
            value = it.value,
            datatype = it.datatype,
            latitude = it.latitude,
            longitude = it.longitude,
        )
    }.toTypedArray()

}

fun List<Weather>.asDiplayList(): List<WeatherRead> {
    return this.map {
        WeatherRead(
            date = it.date,
            type = getTypeWeather(it.datatype),
            value = it.value,
        )
    }

}

fun List<Weather>.asRainStatus(): List<Boolean> {
    return this.map {
        !it.value.equals("no rain", ignoreCase = true)

    }

}