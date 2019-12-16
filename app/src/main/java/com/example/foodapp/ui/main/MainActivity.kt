package com.example.foodapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.category.CategoryActivity
import com.example.foodapp.ui.details.DetailsActivity
import com.example.foodapp.ui.main.adapter.PagerRandomMealAdapter
import com.example.foodapp.ui.main.adapter.RecyclerViewCategoriesAdapter
import com.example.foodapp.ui.search.SearchActivity
import com.example.foodapp.untils.Actions
import com.example.foodapp.untils.SpaceItemDecorator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PagerRandomMealAdapter.OnRandomMealClickListener,
RecyclerViewCategoriesAdapter.CategoryClickListener{

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var pagerAdapter:PagerRandomMealAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var categoryAdapter:RecyclerViewCategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        pagerAdapter = PagerRandomMealAdapter(this)
        pagerAdapter.setListenr(this)
        categoryAdapter = RecyclerViewCategoriesAdapter()
        categoryAdapter.setListener(this)

        initRandomPager()
        initCategories()
        initFakeSearchView()

        coroutineScope.launch {
            val categories = viewModel.categories.await()
            val randomMeal = viewModel.randomMeal.await()

            categories.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                categoryAdapter.setDateList(it)
            })

            randomMeal.observe(this@MainActivity, Observer {
                if (it == null) return@Observer
                pagerAdapter.setDataList(it)
            })
        }
    }

    private fun initFakeSearchView(){
        fake_searchView.setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                fake_searchView, "search_transition")
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
    }

    private fun initRandomPager(){
        viewPager_randomMeal.apply {
            adapter = pagerAdapter
            pageMargin = 24
        }
    }

    private fun initCategories(){
        recyclerView_categories.apply {
            adapter = categoryAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 3,
                GridLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = true
            addItemDecoration(SpaceItemDecorator(5))
        }
    }


    override fun onRandomClick(pos: Int, item: Meal) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.action = Actions.ALREADY_HAVE_DATA
        intent.putExtra(Actions.DETAIL_MEAL, item)
        startActivity(intent)
    }

    override fun onCategoryClick(position: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(Actions.CATEGORY_POSITION, position)
        startActivity(intent)
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}
