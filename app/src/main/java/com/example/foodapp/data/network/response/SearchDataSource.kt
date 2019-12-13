package com.example.foodapp.data.network.response

import androidx.lifecycle.LiveData
import com.example.foodapp.data.model.Meal

interface SearchDataSource {

    val downloadSearchMeal:LiveData<List<Meal>>

    suspend fun searchRequest(request:String)

}