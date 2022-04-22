package com.jamessc94.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "wdatatype")
@JsonClass(generateAdapter = true)
data class WDatatype(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:String = "",

    @ColumnInfo(name = "name")
    var name:String = "",

    @ColumnInfo(name = "datasetid")
    var datasetid:String = "",

    @ColumnInfo(name = "datacategoryid")
    var datacategoryid:String = "",

)

@JsonClass(generateAdapter = true)
data class WDatatypeListContainer(val results : List<WDatatype>)

fun WDatatypeListContainer.asDatabaseModel(): Array<WDatatype> {
    return results.map {
        WDatatype(
            id = it.id,
            name = it.name,
            datasetid = it.datasetid,
            datacategoryid = it.datacategoryid,
        )
    }.toTypedArray()

}