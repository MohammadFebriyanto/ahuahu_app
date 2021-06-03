package com.cap0097.ahuahuapp.data.remote

import com.cap0097.ahuahuapp.data.remote.response.ResultResponse
import com.cap0097.ahuahuapp.utils.Constants
import com.cap0097.ahuahuapp.data.remote.response.ItemsItem
import com.cap0097.ahuahuapp.data.remote.response.RevGeocodeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ENDPOINT_RESULT)
    suspend fun getResult(
        @Query("lat") lat : String,
        @Query("long") long : String
    ) : ResultResponse

    @GET(Constants.ENDPOINT_REVGEOCODE)
    suspend fun getGeocode(
        @Query("at") latLong : String,
        @Query("apiKey") apiKey : String = Constants.API_KEY
    ) : RevGeocodeResponse<ItemsItem>
}