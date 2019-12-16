package com.example.foodapp.ui.category

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.repository.MainRepository
import com.example.foodapp.untils.lazyDeferred
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: MainRepository
) :ViewModel() {

    val categories by lazyDeferred {
        repository.getCategories()
    }
}