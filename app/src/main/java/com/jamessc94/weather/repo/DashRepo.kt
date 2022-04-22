package com.jamessc94.weather.repo

import com.jamessc94.weather.model.WDatatypeListContainer
import com.jamessc94.weather.network.Network
import retrofit2.Response
import javax.inject.Inject

class DashRepo @Inject constructor() {

    suspend fun getWDatatype(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) : Response<WDatatypeListContainer>{

        onStart()

        Network.retroWDatatype.fetchWDatatypeList().apply {
            if(this.isSuccessful){
                onComplete()

            }else{
                onError(this.errorBody()!!.toString())
            }

            return this

        }

    }

}