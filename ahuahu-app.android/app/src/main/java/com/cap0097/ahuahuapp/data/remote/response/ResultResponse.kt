package com.cap0097.ahuahuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("no2")
	val no2: Double,

	@field:SerializedName("o3")
	val o3: Double,

	@field:SerializedName("so2")
	val so2: Double,

	@field:SerializedName("kualitas_udara")
	val kualitasUdara: String,

	@field:SerializedName("pm10")
	val pm10: Double,

	@field:SerializedName("rekomendasi")
	val rekomendasi: String,

	@field:SerializedName("co")
	val co: Double
)
