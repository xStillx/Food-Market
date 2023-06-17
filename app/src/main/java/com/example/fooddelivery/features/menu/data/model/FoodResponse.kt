package com.example.fooddelivery.features.menu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    val id: String,
    @Json(name = "img")
    val image: String,
    val name: String,
    @Json(name = "dsc")
    val description: String,
    val price: Double,
    val rate: Int,
    val country: String
)