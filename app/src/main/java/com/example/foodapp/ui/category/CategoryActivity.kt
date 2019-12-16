package com.example.foodapp.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Category
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.category.adapter.PagerSlidAdapter
import com.example.foodapp.ui.main.MainViewModel
import com.example.foodapp.untils.Actions
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.*
import java.text.FieldPosition
import javax.inject.Inject

class CategoryActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var categoryPosition:Int = 0
    @Inject lateinit var viewModelFactory:BaseViewModelFactory
    private lateinit var viewModel:CategoryViewModel
    private lateinit var pagerSlidAdapter:PagerSlidAdapter
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoryViewModel::class.java)

        mToolbar = findViewById(R.id.category_toolbar)
        setSupportActionBar(mToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        categoryPosition = intent.getIntExtra(Actions.CATEGORY_POSITION, 0)

        initPager()
    }

    private fun initPager() = coroutineScope.launch {
        val categories = viewModel.categories.await()

        categories.observe(this@CategoryActivity, Observer {
            if (it == null) return@Observer
            pagerSlidAdapter = PagerSlidAdapter(supportFragmentManager, it)
            category_slider.apply {
                adapter = pagerSlidAdapter
                currentItem = categoryPosition
            }
            tab_layout.setupWithViewPager(category_slider)
        })
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}
