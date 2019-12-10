package com.example.foodapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.ui.BaseViewModelFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        coroutineScope.launch {
            val categories = viewModel.categories.await()
            val randomMeal = viewModel.randomMeal.await()

            categories.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                Log.e("TEST", it.toString())
            })

            randomMeal.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                for (i in it){
                    Log.e("MEAL", i.toString())
                }
            })
        }

    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}
