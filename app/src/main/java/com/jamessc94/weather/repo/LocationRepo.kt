package com.jamessc94.weather.repo

import com.jamessc94.weather.model.WLocationListContainer
import com.jamessc94.weather.network.Network
import com.jamessc94.weather.utils.WeatherTypeUtils
import retrofit2.Response
import javax.inject.Inject

class LocationRepo @Inject constructor() {

    suspend fun fetchLocationList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        category: String
    ) : Response<WLocationListContainer> {

        onStart()

        Network.retroWLocation.fetchWLocationList(category).apply {
            if(this.isSuccessful){
                onComplete()

            }else{
                onError(this.errorBody()!!.string())

            }

            return this

        }

    }

}