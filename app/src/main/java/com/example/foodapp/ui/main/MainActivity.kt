package com.example.foodapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.main.adapter.PagerMealAdapter
import com.example.foodapp.ui.main.adapter.PagerRandomMealAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var pagerAdapter:PagerMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        pagerAdapter = PagerMealAdapter(supportFragmentManager)

        coroutineScope.launch {
            val categories = viewModel.categories.await()
            val randomMeal = viewModel.randomMeal.await()

            categories.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                Log.e("TEST", it.toString())
            })

            randomMeal.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                pagerAdapter.setDataList(it)
                viewPager_randomMeal.apply {
                    adapter = pagerAdapter
                    pageMargin = 24
                }
            })
        }

    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}
