package com.example.foodapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.network.response.MealDataSource
import com.example.foodapp.data.network.response.MealDetailDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class DetailViewModel @Inject constructor(
    @param:Named("MealDetailDataSource")
    private val detailApi:MealDataSource
):ViewModel() {

    val detailSource = detailApi.downloadData

    fun getMealDetail(id:String){
        GlobalScope.launch {
            detailApi.makeRequest(id)
        }
    }

}