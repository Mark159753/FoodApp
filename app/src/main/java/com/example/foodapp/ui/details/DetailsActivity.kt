package com.example.foodapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.untils.Actions
import com.example.foodapp.untils.Actions.ALREADY_HAVE_DATA
import com.example.foodapp.untils.Actions.NEED_SERVER_REQUEST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

const val DETAIL_MEAL = "DETAIL_MEAL"

class DetailsActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        mToolbar = findViewById(R.id.detail_toolbar)
        setSupportActionBar(mToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initData()
    }

    private fun initData(){
        val action = intent.action
        when (action){
            ALREADY_HAVE_DATA -> {bindUI(intent.getParcelableExtra(DETAIL_MEAL)!!)}

            NEED_SERVER_REQUEST -> {
                val mealId = intent.getStringExtra(DETAIL_MEAL)
                viewModel.detailSource.observe(this, Observer {
                    it.let { bindUI(it[0]) }
                })
                viewModel.getMealDetail(mealId!!)
            }
        }
    }

    private fun bindUI(data:Meal){
        upDateToolBarTitle(data.strMeal)
        setCategory(data.strCategory!!)
        setCountry(data.strArea!!)
        setInstructions(data.strInstructions!!)
        setHeadImg(data.strMealThumb!!)

    }

    private fun upDateToolBarTitle(string: String?){
        supportActionBar?.title = string
    }

    private fun setCategory(category:String){
        detail_category.text = category
    }

    private fun setCountry(country:String){
        detail_country.text = country
    }

    private fun setInstructions(instructions:String){
        detail_instruction.text = instructions
    }

    private fun setHeadImg(imgUrl:String){
        Picasso.get()
            .load(imgUrl)
            .into(detail_head_img)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
