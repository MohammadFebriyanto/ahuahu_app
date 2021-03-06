package com.cap0097.ahuahuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RevGeocodeResponse<T>(

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class Position(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
)

data class MapView(

	@field:SerializedName("east")
	val east: Double,

	@field:SerializedName("south")
	val south: Double,

	@field:SerializedName("north")
	val north: Double,

	@field:SerializedName("west")
	val west: Double
)

data class ItemsItem(

	@field:SerializedName("mapView")
	val mapView: MapView,

	@field:SerializedName("address")
	val address: Address,

	@field:SerializedName("distance")
	val distance: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("position")
	val position: Position,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("resultType")
	val resultType: String
)

data class Address(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("subdistrict")
	val subdistrict: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("district")
	val district: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("county")
	val county: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("countryName")
	val countryName: String? = null
)
