package com.jamessc94.weather.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

object DateUtils {

    private val sdfD = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val sdfDayOfWeek = SimpleDateFormat("EEEE", Locale.US)
    private val sdfDFull = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US)

    fun Date.currentDate() = sdfD.format(this)

    fun Date.getDateDay() = sdfDayOfWeek.format(this)

    fun String.toDate() : String {
        try{
            return sdfD.format(sdfDFull.parse(this)!!)

        }catch (e : Exception){
            e.printStackTrace()

        }

        return this

    }

    fun String.updateDays(days: Int) : Date {
        try{
            val cal = Calendar.getInstance()
            cal.time = sdfD.parse(this)!!
            cal.add(Calendar.DATE, days)

            return cal.time

        }catch (e : Exception){
            e.printStackTrace()

        }

        return Date()

    }

}