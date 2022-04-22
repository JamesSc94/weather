package com.jamessc94.weather.network

import com.jamessc94.weather.model.WLocationListContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WLocationService {

    @GET("locations")
    suspend fun fetchWLocationList(@Query("locationcategoryid") locationcategoryid: String) : Response<WLocationListContainer>

}