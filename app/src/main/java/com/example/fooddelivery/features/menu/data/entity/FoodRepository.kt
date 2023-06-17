package com.example.fooddelivery.features.menu.data.entity

import androidx.lifecycle.LiveData
import com.example.fooddelivery.features.menu.domain.model.Food

class FoodRepository(private val foodDao: FoodDao) {

    val readAllData: LiveData<List<Food>> = foodDao.readData()

    suspend fun addFood(food: Food){
        foodDao.addFood(food)
    }

    suspend fun deleteFood(food: Food){
        foodDao.deleteFood(food)
    }
}