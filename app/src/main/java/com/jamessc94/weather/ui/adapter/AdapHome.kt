package com.jamessc94.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamessc94.weather.databinding.CellHomeBinding
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.model.WeatherRead
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdapHome() : ListAdapter<WeatherRead, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position)
                holder.bind(item)
            }

        }
    }

    class ViewHolder private constructor(val binding: CellHomeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: WeatherRead){
            binding.data = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(CellHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            }

        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<WeatherRead>() {

            override fun areItemsTheSame(oldItem: WeatherRead, newItem: WeatherRead): Boolean =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: WeatherRead, newItem: WeatherRead): Boolean =
                oldItem == newItem
        }
    }

}