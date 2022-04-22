package com.jamessc94.weather.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.jamessc94.weather.databinding.FragLocationBinding
import com.jamessc94.weather.fext.setWidthPercent
import com.jamessc94.weather.ui.adapter.AdapLocation
import com.jamessc94.weather.utils.WeatherTypeUtils.getCategoryLocation

class LocationFrag : DialogFragment() {

    companion object {
        fun newInstance(): LocationFrag {
            return LocationFrag()
        }
    }

    @get:VisibleForTesting
    internal val vm : LocationVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragLocationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.adapter = AdapLocation(activity!!, getCategoryLocation())
        binding.list = getCategoryLocation()

        binding.fragLocationCancel.setOnClickListener {
            dismiss()

        }

        return binding.root

    }

    override fun onResume() {
        super.onResume()

        setWidthPercent(90, 80)

    }

}