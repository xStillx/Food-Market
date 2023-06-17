package com.example.fooddelivery.features.profile.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.features.menu.data.entity.FoodDataBase
import com.example.fooddelivery.features.menu.data.entity.FoodRepository
import com.example.fooddelivery.features.menu.domain.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
): ViewModel() {

    var readAllData: LiveData<List<Food>>? = null
    private var repository: FoodRepository? = null

    fun database(context: Context) {
        val foodDao = FoodDataBase.getDataBase(context = context).foodDao()
        repository = FoodRepository(foodDao)
        readAllData = repository!!.readAllData
    }
}