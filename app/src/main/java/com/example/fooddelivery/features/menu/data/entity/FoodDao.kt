package com.example.fooddelivery.features.menu.data.entity

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fooddelivery.features.menu.domain.model.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food: Food)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readData(): LiveData<List<Food>>

    @Delete
    suspend fun deleteFood(food: Food)
}