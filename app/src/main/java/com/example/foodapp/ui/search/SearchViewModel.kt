package com.example.foodapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.network.response.MealDataSource
import com.example.foodapp.data.network.response.SearchDataSource
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named


class SearchViewModel @Inject constructor(
    private val searchDataSource: SearchDataSource
): ViewModel() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val searchResult = searchDataSource.downloadData

    fun searchRequest(request:String){
        coroutineScope.launch { searchDataSource.makeRequest(request) }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}