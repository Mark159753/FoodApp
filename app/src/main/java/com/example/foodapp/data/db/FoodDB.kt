package com.example.foodapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.data.db.dao.CategoryDao
import com.example.foodapp.data.db.dao.RandomMealDao
import com.example.foodapp.data.db.dao.TimeRequestDao
import com.example.foodapp.data.model.Category
import com.example.foodapp.data.model.Meal
import com.example.foodapp.data.model.TimeRequest

@Database(entities = [Category::class, Meal::class, TimeRequest::class], version = 1)
abstract class FoodDB: RoomDatabase() {

    abstract fun getCategoriesDao():CategoryDao
    abstract fun getRandomMealDao():RandomMealDao
    abstract fun getTimeRequestDao():TimeRequestDao

    companion object{
        @Volatile
        private var instance:FoodDB? = null
        private val LOOK:Any = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOOK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context, FoodDB::class.java, "food_db"
        )
            .build()
    }
}