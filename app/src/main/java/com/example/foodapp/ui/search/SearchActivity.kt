package com.example.foodapp.ui.search

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.search.adapter.RecyclerSearchAdapter
import kotlinx.android.synthetic.main.activity_search_view.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var mToolbar: Toolbar
    private lateinit var searchAdapter:RecyclerSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        mToolbar = findViewById(R.id.search_tool_bar)
        setSupportActionBar(mToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        searchAdapter = RecyclerSearchAdapter()

        initSearchList()
        initResponseListener()
    }

    private fun initResponseListener(){
        searchViewModel.searchResult.observe(this, Observer {
            search_progressBar.visibility = View.INVISIBLE
            searchAdapter.setListData(it)
        })
    }

    private fun initSearchList(){
        search_list.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            setHasFixedSize(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchMenager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu!!.findItem(R.id.search_bar).actionView as SearchView).apply {
            setSearchableInfo(searchMenager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        searchViewModel.searchRequest(it)
                        search_progressBar.visibility = View.VISIBLE
                    }
                    return true
                }
            })
        }
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
