package com.example.fooddelivery.features.menu.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.R
import com.example.fooddelivery.features.menu.data.entity.FoodDataBase
import com.example.fooddelivery.features.menu.data.entity.FoodRepository
import com.example.fooddelivery.features.menu.domain.FoodInteractor
import com.example.fooddelivery.features.menu.domain.model.Food
import com.example.fooddelivery.utils.SingleLiveEvent
import com.example.fooddelivery.utils.ViewState
import com.example.fooddelivery.utils.asLiveData
import com.example.fooddelivery.utils.asViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val foodInteractor: FoodInteractor
) : ViewModel() {

    private val _isVisible = MutableLiveData(false)
    val isVisible get() = _isVisible.asLiveData()

    private val _food = MutableLiveData<ViewState<List<Food>>>()
    val food get() = _food.asLiveData()

    private val _banners = MutableLiveData<List<Int>>()
    val banners get() = _banners.asLiveData()

    private val _categories = MutableLiveData<List<String>>()
    val categories get() = _categories.asLiveData()

    val location = SingleLiveEvent<String>()

    private var repository: FoodRepository? = null

    init {
        getPizzas()
        getBanners()
        getCategories()
    }

    fun dataBase(context: Context){
        val foodDao = FoodDataBase.getDataBase(context = context).foodDao()
        repository = FoodRepository(foodDao)
    }

    fun onAddFoodToBuyClick(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.addFood(food)
        }
    }

    fun getPizzas(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getPizzas().asViewState()
            _isVisible.value = false
        }
    }

    fun getBurgers(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getBurgers().asViewState()
            _isVisible.value = false
        }
    }
    fun getDesserts(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getDesserts().asViewState()
            _isVisible.value = false
        }
    }
    fun getDrinks(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getDrinks().asViewState()
            _isVisible.value = false
        }
    }
    fun getBbqs(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getBbqs().asViewState()
            _isVisible.value = false
        }
    }
    fun getSteaks(){
        _isVisible.value = true
        viewModelScope.launch {
            _food.value = foodInteractor.getSteaks().asViewState()
            _isVisible.value = false
        }
    }

    fun getCity(city: String){
        location.call(city)
    }

    private fun getBanners(){
        val bannersList = listOf(
                R.drawable.banner_1,
                R.drawable.banner_2,
                R.drawable.banner_3,
                R.drawable.banner_4,
                R.drawable.banner_5,
                R.drawable.banner_6,
            )


        _banners.value = bannersList
    }

    private fun getCategories(){
        val categoriesList =
            listOf(
                "Пицца",
                "Бургеры",
                "Десеты",
                "Напитки",
                "Барбекю",
                "Стейки",
                "Мороженное",
            )

        _categories.value = categoriesList
    }
}