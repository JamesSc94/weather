package com.jamessc94.weather.binding

import android.graphics.drawable.TransitionDrawable
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamessc94.weather.R
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.utils.WeatherTypeUtils.getCurrentRainStatus
import com.jamessc94.weather.utils.WeatherTypeUtils.getTimeBg

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?){
        if(!text.isNullOrEmpty()){
            Toast.makeText(view.context, text, Toast.LENGTH_LONG).show()

        }

    }

    @JvmStatic
    @BindingAdapter("visibilityPb")
    fun bindVisibilityPb(view: ProgressBar, b: Boolean){
        view.visibility = if(b) View.VISIBLE else View.GONE

    }

    @JvmStatic
    @BindingAdapter("rain")
    fun bindRain(view: View, weathers: List<Weather>?){
        if(!weathers.isNullOrEmpty()){
            val transitionDrawable = TransitionDrawable(arrayOf(ContextCompat.getDrawable(view.context, R.drawable.bg_empty), getTimeBg(view.context, getCurrentRainStatus(weathers))))
            view.background = transitionDrawable
            transitionDrawable.startTransition(1000)

        }

    }

}