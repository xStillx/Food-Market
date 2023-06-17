package com.example.fooddelivery.features.menu.di

import com.example.fooddelivery.features.menu.data.api.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class FoodFeaturesModule {

    @Provides
    fun provideFoodApi(retrofit: Retrofit) = retrofit.create(
        FoodApi::class.java
    )
}