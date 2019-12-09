package com.example.foodapp.data.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Meal
import com.example.foodapp.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NetworkDataSourseImpl @Inject constructor(
    private val apiService: ApiService
): NetworkDataSource {

    private val _downwloadedCategories = MutableLiveData<CategoriesResponse>()
    override val downwloadedCategories: LiveData<CategoriesResponse>
        get() = _downwloadedCategories
    private val _downloadMealRandom = MutableLiveData<List<Meal>>()
    override val downloadMealRandom: LiveData<List<Meal>>
        get() = _downloadMealRandom

    override suspend fun featchCategories() {
        try {
            val response = withContext(Dispatchers.IO){
                apiService.getCategories()
            }
            if (response.isSuccessful){
                _downwloadedCategories.postValue(response.body())
            }else{
                Log.e("Download ERROR CODE", response.code().toString())
            }
        }catch (e:Exception){
            Log.e("Connectivity", e.message)
        }
    }

    override suspend fun featchMealRandom() {
        try {
            val tempRes:MutableList<Meal> = arrayListOf()
            for (i in 1..5) {
                val response = withContext(Dispatchers.IO) {
                    apiService.getRandomReceipe()
                }
                if (response.isSuccessful){
                    tempRes.add(response.body()!!.meals[0])
                }else{
                    Log.e("Download ERROR CODE", response.code().toString())
                }
            }
            if (tempRes.isNotEmpty()) _downloadMealRandom.postValue(tempRes)
        }catch (e:Exception){
            Log.e("Connectivity", e.message)
        }
    }
}