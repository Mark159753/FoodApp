package com.example.foodapp.data.network.response

import androidx.lifecycle.LiveData
import com.example.foodapp.data.model.Meal

interface SearchDataSource {

    val downloadData: LiveData<List<Meal>>

    suspend fun makeRequest(request:String)
}