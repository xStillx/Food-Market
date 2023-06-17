package com.example.fooddelivery.features.menu.data

import com.example.fooddelivery.features.menu.data.api.FoodApi
import com.example.fooddelivery.features.menu.data.model.mapper.FoodMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRepo @Inject constructor(
    private val api: FoodApi,
    private val foodMapper: FoodMapper
) {

    suspend fun getPizzas() = withContext(Dispatchers.IO){
        api.getPizzas().map {
            foodMapper.map(it)
        }
    }

    suspend fun getBurgers() = withContext(Dispatchers.IO){
        api.getBurgers().map {
            foodMapper.map(it)
        }
    }

    suspend fun getBbqs() = withContext(Dispatchers.IO){
        api.getBbqs().map {
            foodMapper.map(it)
        }
    }

    suspend fun getDesserts() = withContext(Dispatchers.IO){
        api.getDesserts().map {
            foodMapper.map(it)
        }
    }

    suspend fun getDrinks() = withContext(Dispatchers.IO){
        api.getDrinks().map {
            foodMapper.map(it)
        }
    }

    suspend fun getSteaks() = withContext(Dispatchers.IO){
        api.getSteaks().map {
            foodMapper.map(it)
        }
    }
}