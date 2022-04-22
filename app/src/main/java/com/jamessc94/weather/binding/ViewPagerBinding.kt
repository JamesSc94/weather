package com.jamessc94.weather.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jamessc94.weather.model.WSavedLocation
import com.jamessc94.weather.ui.adapter.AdapDash
import com.jamessc94.weather.ui.adapter.AdapLocation
import com.jamessc94.weather.utils.WeatherTypeUtils

object ViewPagerBinding {

    @JvmStatic
    @BindingAdapter("tab", "adapter", "list")
    fun bindAdapter(view : ViewPager2, tab: TabLayout, adapter: FragmentStateAdapter, list: List<String>){
        view.adapter = adapter

        TabLayoutMediator(tab, view){ t, pos ->
            t.text = list[pos]

        }.attach()

    }

}