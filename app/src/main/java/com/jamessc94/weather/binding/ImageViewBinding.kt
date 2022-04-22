package com.jamessc94.weather.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jamessc94.weather.R

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter("weatherType", "weatherValue")
    fun bindWeatherImg(view: ImageView, weatherType: String, weatherValue: String){
        when(weatherType){
            "MORNING", "AFTERNOON" -> {
                if(weatherValue.equals("rain", ignoreCase = true) || weatherValue.contains("thunderstorms", ignoreCase = true) || weatherValue.contains("isolated rain", ignoreCase = true) ){
                    Glide.with(view.context).load(R.drawable.rain).into(view)

                }else if(weatherValue.equals("no rain", ignoreCase = true)){
                    Glide.with(view.context).load(R.drawable.sunny).into(view)

                }else{
                    Glide.with(view.context).load(R.drawable.data).into(view)

                }

            }
            "NIGHT" -> {
                if(weatherValue.equals("rain", ignoreCase = true) || weatherValue.contains("thunderstorms", ignoreCase = true) ){
                    Glide.with(view.context).load(R.drawable.rain).into(view)

                }else{
                    Glide.with(view.context).load(R.drawable.night).into(view)

                }

            }
            else -> {
                Glide.with(view.context).load(R.drawable.data).into(view)

            }

        }


    }

}