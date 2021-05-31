package com.aprian1337.sarap.data.remote.response

import com.google.gson.annotations.SerializedName

data class LatLongResponse(

	@field:SerializedName("no2")
	val no2: Double,

	@field:SerializedName("o3")
	val o3: Int,

	@field:SerializedName("so2")
	val so2: Double,

	@field:SerializedName("kualitas_udara")
	val kualitasUdara: String,

	@field:SerializedName("pm10")
	val pm10: Int,

	@field:SerializedName("rekomendasi")
	val rekomendasi: String,

	@field:SerializedName("co")
	val co: Int
)
