package com.example.foodapp.di.modules

import android.content.Context
import com.example.foodapp.data.db.FoodDB
import com.example.foodapp.data.db.dao.CategoryDao
import com.example.foodapp.data.db.dao.RandomMealDao
import com.example.foodapp.data.db.dao.CategoriesTimeDao
import com.example.foodapp.data.db.dao.TimeRandomMealDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context):FoodDB{
        return FoodDB.invoke(context)
    }

    @Provides
    @Singleton
    fun provideCategoryDao(foodDB: FoodDB):CategoryDao{
        return foodDB.getCategoriesDao()
    }

    @Provides
    @Singleton
    fun provideRandomMealDao(foodDB: FoodDB):RandomMealDao{
        return foodDB.getRandomMealDao()
    }

    @Provides
    @Singleton
    fun provideTimeRequestDao(foodDB: FoodDB):CategoriesTimeDao{
        return foodDB.getTimeCategoriesDao()
    }

    @Provides
    @Singleton
    fun provideTimeRandomMEalDao(foodDB: FoodDB):TimeRandomMealDao{
        return foodDB.getTimeRandomMealDato()
    }
}