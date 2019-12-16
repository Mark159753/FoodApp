package com.example.foodapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodapp.FoodApp
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.example.foodapp.ui.BaseViewModelFactory
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
        updateIngredients(data)
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

    private fun updateIngredients(meal: Meal){
        val data = getIngredients(meal)
        detail_ingredients.text = data[0]
        detail_measure.text = data[1]
    }

    private fun getIngredients(meal:Meal):Array<String>{
        val ingredients = StringBuilder()
        val measure = StringBuilder()

        if (notEmpty(meal.strIngredient1)) {ingredients.append(meal.strIngredient1+"\n"); measure.append(meal.strMeasure1+"\n")}
        if (notEmpty(meal.strIngredient2)) {ingredients.append(meal.strIngredient2+"\n"); measure.append(meal.strMeasure2+"\n")}
        if (notEmpty(meal.strIngredient3)) {ingredients.append(meal.strIngredient3+"\n"); measure.append(meal.strMeasure3+"\n")}
        if (notEmpty(meal.strIngredient4)) {ingredients.append(meal.strIngredient4+"\n"); measure.append(meal.strMeasure4+"\n")}
        if (notEmpty(meal.strIngredient5)) {ingredients.append(meal.strIngredient5+"\n"); measure.append(meal.strMeasure5+"\n")}
        if (notEmpty(meal.strIngredient6)) {ingredients.append(meal.strIngredient6+"\n"); measure.append(meal.strMeasure6+"\n")}
        if (notEmpty(meal.strIngredient7)) {ingredients.append(meal.strIngredient7+"\n"); measure.append(meal.strMeasure7+"\n")}
        if (notEmpty(meal.strIngredient8)) {ingredients.append(meal.strIngredient8+"\n"); measure.append(meal.strMeasure8+"\n")}
        if (notEmpty(meal.strIngredient9)) {ingredients.append(meal.strIngredient9+"\n"); measure.append(meal.strMeasure9+"\n")}
        if (notEmpty(meal.strIngredient10)) {ingredients.append(meal.strIngredient10+"\n"); measure.append(meal.strMeasure10+"\n")}
        if (notEmpty(meal.strIngredient11)) {ingredients.append(meal.strIngredient11+"\n"); measure.append(meal.strMeasure11+"\n")}
        if (notEmpty(meal.strIngredient12)) {ingredients.append(meal.strIngredient12+"\n"); measure.append(meal.strMeasure12+"\n")}
        if (notEmpty(meal.strIngredient13)) {ingredients.append(meal.strIngredient13+"\n"); measure.append(meal.strMeasure13+"\n")}
        if (notEmpty(meal.strIngredient14)) {ingredients.append(meal.strIngredient14+"\n"); measure.append(meal.strMeasure14+"\n")}
        if (notEmpty(meal.strIngredient15)) {ingredients.append(meal.strIngredient15+"\n"); measure.append(meal.strMeasure15+"\n")}
        if (notEmpty(meal.strIngredient16)) {ingredients.append(meal.strIngredient16+"\n"); measure.append(meal.strMeasure16+"\n")}
        if (notEmpty(meal.strIngredient17)) {ingredients.append(meal.strIngredient17+"\n"); measure.append(meal.strMeasure17+"\n")}
        if (notEmpty(meal.strIngredient18)) {ingredients.append(meal.strIngredient18+"\n"); measure.append(meal.strMeasure18+"\n")}
        if (notEmpty(meal.strIngredient19)) {ingredients.append(meal.strIngredient19+"\n"); measure.append(meal.strMeasure19+"\n")}
        if (notEmpty(meal.strIngredient20)) {ingredients.append(meal.strIngredient20+"\n"); measure.append(meal.strMeasure20+"\n")}

        return arrayOf(ingredients.toString(), measure.toString())
    }

    private fun notEmpty(ingredient:String?):Boolean{
        if (ingredient != null && ingredient != ""){
            return true
        }
        return false
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
