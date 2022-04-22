package com.jamessc94.weather.network

import com.jamessc94.weather.model.WDatatypeListContainer
import retrofit2.Response
import retrofit2.http.GET

interface WDatatypeService {

    @GET("datatypes")
    suspend fun fetchWDatatypeList() : Response<WDatatypeListContainer>

}