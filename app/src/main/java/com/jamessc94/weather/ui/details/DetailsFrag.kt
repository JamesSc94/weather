package com.jamessc94.weather.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jamessc94.weather.databinding.FragDetailsBinding

class DetailsFrag : Fragment() {

    companion object {
        const val LID_KEY= "locationid"

        fun newInstance(lid: String): DetailsFrag{
            val fragment = DetailsFrag().apply{

                arguments =  Bundle().apply {
                    putString(LID_KEY, lid)
                }

            }

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root

    }
}