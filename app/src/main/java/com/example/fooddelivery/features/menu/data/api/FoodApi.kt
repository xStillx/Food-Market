package com.example.fooddelivery.features.menu.data.api

import com.example.fooddelivery.features.menu.data.model.FoodResponse
import retrofit2.http.GET

interface FoodApi {

    @GET("pizzas")
    suspend fun getPizzas(): List<FoodResponse>

    @GET("burgers")
    suspend fun getBurgers(): List<FoodResponse>

    @GET("bbqs")
    suspend fun getBbqs(): List<FoodResponse>

    @GET("desserts")
    suspend fun getDesserts(): List<FoodResponse>

    @GET("drinks")
    suspend fun getDrinks(): List<FoodResponse>

    @GET("steaks")
    suspend fun getSteaks(): List<FoodResponse>

}