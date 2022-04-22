package com.jamessc94.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamessc94.weather.R
import com.jamessc94.weather.databinding.CelllLocationDetailsBinding
import com.jamessc94.weather.model.WLocationAdap

class AdapLocationDetails(val clickListener: AdapLocationDetailsClickListener) : ListAdapter<WLocationAdap, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position)
                holder.bind(clickListener, item)
            }

        }
    }

    class ViewHolder private constructor(val binding: CelllLocationDetailsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: AdapLocationDetailsClickListener, item: WLocationAdap){
            binding.data = item
            binding.click = clickListener

            if(item.locationid.isNullOrEmpty()) {
                binding.buttontext = binding.root.context.resources.getString(R.string.add)
                binding.buttontextcolor = ContextCompat.getColor(binding.root.context, R.color.custom_primary)

            } else {
                binding.buttontext = binding.root.context.resources.getString(R.string.remove)
                binding.buttontextcolor = ContextCompat.getColor(binding.root.context, R.color.red_light)

            }

            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(CelllLocationDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            }

        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<WLocationAdap>() {

            override fun areItemsTheSame(oldItem: WLocationAdap, newItem: WLocationAdap): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WLocationAdap, newItem: WLocationAdap): Boolean =
                oldItem == newItem
        }
    }

}

class AdapLocationDetailsClickListener(val clickListener: (item: WLocationAdap) -> Unit) {
    fun onClick(item: WLocationAdap) = clickListener(item)

}