package com.jamessc94.weather.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.jamessc94.weather.databinding.FragDashBinding
import com.jamessc94.weather.ui.adapter.AdapDash
import com.jamessc94.weather.ui.location.LocationFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashFrag : Fragment() {

    private val vm : DashVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragDashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.fragDashAdd.setOnClickListener {
            LocationFrag.newInstance().show(childFragmentManager, "locations")

        }

        vm.wsavedlocations.observe(viewLifecycleOwner, Observer {
            binding.fragDashIniatemsg.visibility = if(it.isEmpty()) VISIBLE else GONE

            if(binding.fragDashVp.adapter == null){
                binding.fragDashVp.adapter = AdapDash(activity!!, it.toMutableList())

                TabLayoutMediator(binding.fragDashTl, binding.fragDashVp){ _, _ ->

                }.attach()

            }else{
                (binding.fragDashVp.adapter as AdapDash).refresh(it)

            }

        })

        return binding.root

    }
}