package com.jamessc94.weather.utils

import android.graphics.Color
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class WeatherTypeUtilsTest : TestCase() {

    lateinit var SUT: WeatherTypeUtils

    @Before
    fun setup(){
        SUT = WeatherTypeUtils
    }

    @Test
    fun testBlack(){
        val result = WeatherTypeUtils.getTypeColor("Rain")
        assertEquals(Color.BLUE, result)
    }

}