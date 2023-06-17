package com.example.fooddelivery.features.menu.data.model.mapper

import com.example.fooddelivery.features.menu.data.model.FoodResponse
import com.example.fooddelivery.features.menu.domain.model.Food
import javax.inject.Inject

class FoodMapper @Inject constructor() {

    fun map(foodResponse: FoodResponse) = Food(
        id = foodResponse.id,
        image = foodResponse.image,
        name = foodResponse.name,
        description = foodResponse.description,
        price = foodResponse.price,
        rate = foodResponse.rate,
        country = foodResponse.country
    )
}