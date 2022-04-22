package com.jamessc94.weather.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.model.WeatherRead
import com.jamessc94.weather.model.asDiplayList


object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view : RecyclerView, adapter: RecyclerView.Adapter<*>){
        view.adapter = adapter

    }

    @JvmStatic
    @BindingAdapter("deco")
    fun bindDeco(view : RecyclerView, b: Boolean){
        if(b){
            view.addItemDecoration(
                DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL)

            )

        }

    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?){
        (view.adapter as ListAdapter<Any, *>).let {
            it.submitList(itemList)

        }

    }

    @JvmStatic
    @BindingAdapter("submitWeatherDisplayList")
    fun bindSubmitWeatherDisplayList(view: RecyclerView, itemList: List<Weather>?){
        (view.adapter as ListAdapter<Any, *>).let {
            if(itemList is List<Weather>){
                it.submitList(itemList.asDiplayList())

            }

        }

    }

}