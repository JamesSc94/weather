package com.jamessc94.weather.ui.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jamessc94.weather.databinding.FragLocationDetailsBinding
import com.jamessc94.weather.ui.adapter.AdapLocationDetails
import com.jamessc94.weather.ui.adapter.AdapLocationDetailsClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailsFrag : Fragment() {

    companion object {
        const val LTYPE_KEY= "locationtype"

        fun newInstance(ltype: String): LocationDetailsFrag {
            val fragment = LocationDetailsFrag().apply{

                arguments =  Bundle().apply {
                    putString(LTYPE_KEY, ltype)
                }

            }

            return fragment
        }
    }

    val vm : LocationDetailsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragLocationDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.adapter = AdapLocationDetails(AdapLocationDetailsClickListener{
            vm.updateSavedLocation(it, !it.locationid.isNullOrEmpty())

        })

        binding.vm = vm

        return binding.root

    }

}