package com.example.foodapp.data.network.response

import androidx.lifecycle.LiveData
import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Meal

interface NetworkDataSource {

    val downwloadedCategories:LiveData<CategoriesResponse>
    val downloadMealRandom:LiveData<List<Meal>>

    suspend fun featchCategories()

    suspend fun featchMealRandom()
}