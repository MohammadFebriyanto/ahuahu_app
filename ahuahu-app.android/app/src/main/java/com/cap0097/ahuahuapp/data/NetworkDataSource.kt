package com.cap0097.ahuahuapp.data

import com.cap0097.ahuahuapp.data.remote.ApiService
import com.cap0097.ahuahuapp.data.remote.response.Address
import com.cap0097.ahuahuapp.data.remote.response.ItemsItem
import com.cap0097.ahuahuapp.data.remote.response.ResultResponse
import com.cap0097.ahuahuapp.data.remote.response.RevGeocodeResponse
import retrofit2.await
import javax.inject.Inject
import javax.inject.Named

class NetworkDataSource @Inject constructor(
    @Named("resultApi") private val resultApi: ApiService,
    @Named("geocodeApi") private val geocodeApi: ApiService
) {
    suspend fun getResult(lat: String, long: String, callback: LoadResultCallback) {
        val response = resultApi.getResult(lat,long)
        response.await().apply {
            callback.onResultReceived(this)
        }
    }

    suspend fun getGeocode(latLong: String, callback: LoadGeocodeCallback) {
        val response = geocodeApi.getGeocode(latLong)
        response.await().items.apply {
            callback.onGeocodeReceived(this)
        }
    }

    interface LoadResultCallback {
        fun onResultReceived(resultResponse: ResultResponse)
    }

    interface LoadGeocodeCallback {
        fun onGeocodeReceived(geocodeResponse: List<ItemsItem>)
    }
}