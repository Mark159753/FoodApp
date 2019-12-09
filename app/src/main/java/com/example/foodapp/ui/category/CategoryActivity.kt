package com.example.foodapp.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.R

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }
}
