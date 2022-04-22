package com.jamessc94.weather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jamessc94.weather.databinding.FragHomeBinding
import com.jamessc94.weather.model.asDiplayList
import com.jamessc94.weather.ui.adapter.AdapHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag : Fragment() {

    companion object {
        const val LID_KEY= "locationid"
        const val LNAME_KEY= "locationname"

        fun newInstance(lid: String, lname: String): HomeFrag {
            val fragment = HomeFrag().apply{

                arguments =  Bundle().apply {
                    putString(LID_KEY, lid)
                    putString(LNAME_KEY, lname)
                }

            }

            return fragment
        }
    }

    @get:VisibleForTesting
    internal val vm : HomeVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = vm
        binding.adapter = AdapHome()

        vm.liveClearData.observe(viewLifecycleOwner, Observer {
            if(it){
                (binding.adapter as AdapHome).let { adap ->
                    adap.submitList(null)

                }

                vm.liveClearData.value = false

            }

        })

        vm.weathers.observe(viewLifecycleOwner, Observer {
            (binding.adapter as AdapHome).let { adap ->
                adap.submitList(it.asDiplayList().filter { mod -> mod.date.equals(vm.liveDate.value, ignoreCase = true)})

            }

        })

        return binding.root

    }
}