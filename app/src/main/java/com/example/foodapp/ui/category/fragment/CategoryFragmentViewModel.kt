package com.example.foodapp.ui.category.fragment

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.network.response.CategoryMealDataSource
import kotlinx.coroutines.*
import javax.inject.Inject

class CategoryFragmentViewModel @Inject constructor(
    private val categoryMealDataSource: CategoryMealDataSource
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