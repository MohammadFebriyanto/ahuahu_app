package com.aprian1337.sarap.utils

import com.cap0097.ahuahuapp.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.AUTH_TOKEN
    const val TABLE_NAME_HISTORY = "history"
    const val DATABASE_NAME = "ahuahu.db"

//    https://revgeocode.search.hereapi.com/v1/revgeocode?at=-8.531786464264338,114.11045073249807&apiKey=D0oCHEXtEw1cDXngPgEXdh4NI9JtqNB1eqKtIc-nj6E
    const val BASE_URL_GEOCODE = "https://revgeocode.search.hereapi.com/v1/"
    const val ENDPOINT_REVGEOCODE = "revgeocode"

//   ==========================

    const val BASE_URL = "http://34.126.169.120/api/v1/"
    const val ENDPOINT_RESULT = "resources/air"
}