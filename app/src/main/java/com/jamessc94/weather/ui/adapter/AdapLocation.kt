package com.jamessc94.weather.ui.adapter

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jamessc94.weather.ui.location.LocationDetailsFrag
import dagger.hilt.android.qualifiers.ActivityContext

class AdapLocation constructor(
    fa : FragmentActivity,
    val wlocations: List<String>
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return wlocations.size

    }

    override fun createFragment(pos: Int): Fragment {
        return LocationDetailsFrag.newInstance(wlocations[pos])

    }

}