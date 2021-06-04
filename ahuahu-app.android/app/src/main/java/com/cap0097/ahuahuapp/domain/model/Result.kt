package com.cap0097.ahuahuapp.domain.model

data class Result(
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val kualitasUdara: String,
    val pm10: Double,
    val rekomendasi: String,
    val co: Double,

    val city: String?=null,
    val countryCode: String?=null,
    val subdistrict: String?=null,
    val street: String?=null,
    val district: String?=null,
    val postalCode: String?=null,
    val county: String?=null,
    val label: String?=null,
    val countryName: String?=null
)