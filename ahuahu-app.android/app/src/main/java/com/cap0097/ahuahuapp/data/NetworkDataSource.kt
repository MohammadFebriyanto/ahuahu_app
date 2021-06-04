package com.cap0097.ahuahuapp.data

import com.cap0097.ahuahuapp.data.remote.ApiService
import com.cap0097.ahuahuapp.domain.model.Result
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class NetworkDataSource @Inject constructor(
    @Named("resultApi") private val resultApi: ApiService,
    @Named("geocodeApi") private val geocodeApi: ApiService
) {
    suspend fun getResult(lat: String, long: String, callback: LoadResultCallback) {
        val responseGeocode = geocodeApi.getGeocode("${lat},${long}")
        val response = resultApi.getResult(lat, long)
        try {
            response.let { result->
                responseGeocode.items[0].address.let { geocode->
                    val resultModel = Result(
                        result.no2,
                        result.o3,
                        result.so2,
                        result.kualitasUdara,
                        result.pm10,
                        result.rekomendasi,
                        result.co,
                        result.deskripsi,
                        result.link,
                        geocode.city.toString(),
                        geocode.countryCode.toString(),
                        geocode.subdistrict.toString(),
                        geocode.street.toString(),
                        geocode.district.toString(),
                        geocode.postalCode.toString(),
                        geocode.county.toString(),
                        geocode.label.toString(),
                        geocode.countryName.toString(),
                    )
                    callback.onResultReceived(Resource.Success(resultModel))
                }
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    interface LoadResultCallback {
        fun onResultReceived(results: Resource<Result>)
    }
}