package com.jamessc94.weather.ui.adapter

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jamessc94.weather.model.WSavedLocation
import com.jamessc94.weather.ui.home.HomeFrag
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AdapDash constructor(
    @ActivityContext ctx : Context,
    val wsavedlocations : MutableList<WSavedLocation>
) : FragmentStateAdapter(ctx as AppCompatActivity) {

    fun refresh(newlocations: List<WSavedLocation>){
        if(newlocations.size > wsavedlocations.size){
            wsavedlocations.add(newlocations[newlocations.size - 1])
            notifyItemInserted(wsavedlocations.size - 1)

        }else{
            wsavedlocations.forEachIndexed{ index, value ->
                if(newlocations.none { it.locationid == value.locationid } || index == wsavedlocations.size - 1){
                    wsavedlocations.removeAt(index)
                    notifyItemRemoved(index)
                    return

                }

            }

        }

    }

    override fun getItemCount(): Int {
        return wsavedlocations.size

    }

    override fun getItemId(position: Int): Long {
        return wsavedlocations[position].hashCode().toLong()

    }

    override fun createFragment(pos: Int): Fragment {
        return HomeFrag.newInstance(wsavedlocations[pos].locationid, wsavedlocations[pos].name)

    }



}
