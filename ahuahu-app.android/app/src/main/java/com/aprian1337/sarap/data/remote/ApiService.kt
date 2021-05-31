package com.aprian1337.sarap.data.remote

import com.aprian1337.sarap.data.remote.response.LatLongResponse
import com.aprian1337.sarap.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.BASE_URL)
    fun getLotLang(
        @Query("lat") lat : Double,
        @Query("long") long : Double
    ) : Call<LatLongResponse>
}