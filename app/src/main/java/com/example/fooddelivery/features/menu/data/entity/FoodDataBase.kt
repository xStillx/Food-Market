package com.example.fooddelivery.features.menu.data.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddelivery.features.menu.domain.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDataBase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object{

        private var INSTANCE: FoodDataBase? = null

        fun getDataBase(context: Context): FoodDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDataBase::class.java,
                    "food_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}