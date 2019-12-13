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

class SearchDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): SearchDataSource {
    private val _downloadSearchMeal = MutableLiveData<List<Meal>>()
    override val downloadSearchMeal: LiveData<List<Meal>>
        get() = _downloadSearchMeal

    override suspend fun searchRequest(request: String) {
        try {
            val response = withContext(Dispatchers.IO){
                apiService.searchRequest(request)
            }
            if (response.isSuccessful){
                _downloadSearchMeal.postValue(response.body()?.meals)
            }else{
                Log.e("Download ERROR CODE", response.code().toString())
            }
        }catch (e: Exception){
            Log.e("Connectivity", e.message)
        }
    }

}