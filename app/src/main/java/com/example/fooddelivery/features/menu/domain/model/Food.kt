package com.example.fooddelivery.features.menu.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val name: String,
    val description: String,
    val price: Double,
    val rate: Int,
    val country: String
)