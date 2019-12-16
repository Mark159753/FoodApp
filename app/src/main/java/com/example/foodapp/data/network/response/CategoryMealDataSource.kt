package com.example.foodapp.data.network.response

import androidx.lifecycle.LiveData
import com.example.foodapp.data.model.Meal

interface CategoryMealDataSource {

    val downloadData: LiveData<List<Meal>>

    suspend fun makeRequest(request:String)
}