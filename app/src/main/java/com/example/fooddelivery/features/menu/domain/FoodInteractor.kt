package com.example.fooddelivery.features.menu.domain

import com.example.fooddelivery.features.menu.data.FoodRepo
import com.example.fooddelivery.utils.safeRequest
import javax.inject.Inject

class FoodInteractor @Inject constructor(
    private val foodRepo: FoodRepo
) {

    suspend fun getPizzas() = safeRequest {
        foodRepo.getPizzas()
    }

    suspend fun getBurgers() = safeRequest {
        foodRepo.getBurgers()
    }

    suspend fun getBbqs() = safeRequest {
        foodRepo.getBbqs()
    }

    suspend fun getDesserts() = safeRequest {
        foodRepo.getDesserts()
    }

    suspend fun getDrinks() = safeRequest {
        foodRepo.getDrinks()
    }

    suspend fun getSteaks() = safeRequest {
        foodRepo.getSteaks()
    }
}