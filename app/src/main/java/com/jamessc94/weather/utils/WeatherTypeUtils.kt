package com.jamessc94.weather.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.jamessc94.weather.R
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.model.asRainStatus
import java.util.*

object WeatherTypeUtils {

    fun getTypeColor(type: String): Int{
        return when(type){
            "Rain" -> Color.BLACK
            else -> Color.WHITE

        }

    }

    fun getTypeWeather(type: String): String{
        return when(type){
            "FGM" -> "MORNING"
            "FGA" -> "AFTERNOON"
            "FGN" -> "NIGHT"
            "FMAXT" -> "MAXIMUM TEMPERATURE"
            "FMINT" -> "MINIMUM TEMPERATURE"
            "FSIGW" -> "SIGNIFICANT WEATHER"
            else -> type

        }

    }

    fun getTimeBg(ctx: Context, rain: Boolean?): Drawable{
        if(rain == null){
            return ContextCompat.getDrawable(ctx, R.drawable.bg_empty)!!

        }

        return when(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)){
            in 0..6, in 18..23 -> if(rain) ContextCompat.getDrawable(ctx, R.drawable.bg_night_rain)!! else ContextCompat.getDrawable(ctx, R.drawable.bg_night_norain)!!
            in 7..11 -> if(rain) ContextCompat.getDrawable(ctx, R.drawable.bg_morning_rain)!! else ContextCompat.getDrawable(ctx, R.drawable.bg_morning_norain)!!
            in 12..17 -> if(rain) ContextCompat.getDrawable(ctx, R.drawable.bg_afternoon_rain)!! else ContextCompat.getDrawable(ctx, R.drawable.bg_afternoon_norain)!!
            else -> ContextCompat.getDrawable(ctx, R.drawable.night)!!

        }

    }

    fun getCurrentRainStatus(weathers: List<Weather>) : Boolean?{
        return when(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)){
            in 0..6, in 18..23 -> weathers.filter { getTypeWeather(it.datatype).equals("night", ignoreCase = true) }.asRainStatus()[0]
            in 7..11 -> weathers.filter { getTypeWeather(it.datatype).equals("morning", ignoreCase = true) }.asRainStatus()[0]
            in 12..17 -> weathers.filter { getTypeWeather(it.datatype).equals("afternoon", ignoreCase = true) }.asRainStatus()[0]
            else -> null

        }

    }

    fun getCategoryLocation() : List<String> {
        return listOf("STATE", "DISTRICT", "TOWN", "TOURISTDEST")

    }

    enum class categoryLocation(val cat: String){
        STATE("STATE"),
        DISTRICT("DISTRICT"),
        TOWN("TOWN"),
        TOURISTDEST("TOURISTDEST"),
        WATERS("WATERS")
    }

}