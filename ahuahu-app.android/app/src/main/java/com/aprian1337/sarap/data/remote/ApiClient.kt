package com.aprian1337.sarap.data.remote

import com.aprian1337.sarap.utils.Constants
import retrofit2.Retrofit

object ApiClient {
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .build()
}