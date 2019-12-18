package com.example.foodapp.data.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.data.model.Meal
import com.example.foodapp.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CategoryMealDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): MealDataSource {

    private val _downloadData = MutableLiveData<List<Meal>>()
    override val downloadData: LiveData<List<Meal>>
        get() = _downloadData

    override suspend fun makeRequest(request: String) {
        try {
            val response = withContext(Dispatchers.IO){
                apiService.getMealByCategory(request)
            }
            if (response.isSuccessful){
                _downloadData.postValue(response.body()?.meals)
            }else{
                Log.e("Download ERROR CODE", response.code().toString())
            }
        }catch (e: Exception){
            Log.e("Connectivity", e.message)
        }
    }
}