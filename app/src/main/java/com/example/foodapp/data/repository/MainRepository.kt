package com.example.foodapp.data.repository

import androidx.lifecycle.LiveData
import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Category
import com.example.foodapp.data.model.Meal

interface MainRepository {

    suspend fun getCategories():LiveData<List<Category>>
    suspend fun getRandomMeals():LiveData<List<Meal>>
}