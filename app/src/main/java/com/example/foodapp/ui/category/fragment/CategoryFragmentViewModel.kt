package com.example.foodapp.ui.category.fragment

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.network.response.MealDataSource
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

class CategoryFragmentViewModel @Inject constructor(
    @param:Named("CategoryMealDataSourceImpl")
    private val categoryMealDataSource: MealDataSource
):ViewModel() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val categoriesMeal = categoryMealDataSource.downloadData

    fun categoryRequest(request:String){
        coroutineScope.launch { categoryMealDataSource.makeRequest(request) }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}