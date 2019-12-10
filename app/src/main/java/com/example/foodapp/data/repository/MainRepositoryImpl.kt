package com.example.foodapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.foodapp.data.db.dao.CategoryDao
import com.example.foodapp.data.db.dao.RandomMealDao
import com.example.foodapp.data.db.dao.CategoriesTimeDao
import com.example.foodapp.data.db.dao.TimeRandomMealDao
import com.example.foodapp.data.model.*
import com.example.foodapp.data.network.response.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

private const val MINUTE = 60 * 1000
private const val HOUR = MINUTE * 60

class MainRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
    private val categoryDao:CategoryDao,
    private val randomMealDao: RandomMealDao,
    private val categoriesTimeDao: CategoriesTimeDao,
    private val timeRandomMealDao: TimeRandomMealDao
): MainRepository {

    init {
        dataSource.apply {
            downloadMealRandom.observeForever{
                persistRandomMeal(it)
            }

            downwloadedCategories.observeForever{
                persistCategory(it)
            }
        }
    }


    override suspend fun getCategories(): LiveData<List<Category>> {
        return withContext(Dispatchers.IO){
            initCategory()
            return@withContext categoryDao.getCategories()
        }
    }

    override suspend fun getRandomMeals(): LiveData<List<Meal>> {
        return withContext(Dispatchers.IO){
            initRandomMeals()
            return@withContext randomMealDao.getRandomMeal()
        }
    }


    private suspend fun initCategory(){
        val lastRequests = categoriesTimeDao.getAllTimesRequests()

        if (lastRequests == null){
            featchCategory()
            return
        }

        if (fetchIsNeeded(Date(lastRequests.lastTimeCategory!!), 3)){
            featchCategory()
            Log.e("PRINTRANDOM", "C")
        }
    }

    private suspend fun initRandomMeals(){
        val lastRequests = timeRandomMealDao.getAllTimesRequests()

        if (lastRequests == null){
            featchRandomMeal()
            return
        }

        if (fetchIsNeeded(Date(lastRequests.lastTimeRandomMealRequest!!), 1)){
            featchRandomMeal()
            Log.e("PRINTRANDOM", "R")
        }
    }

    private fun persistCategory(category:CategoriesResponse){
        GlobalScope.launch(Dispatchers.IO){
            categoryDao.deleteAllCategories()
            categoryDao.insert(category.categories)

            categoriesTimeDao.upsert(TimeCategoriesRequest(1, Date().time))

            val test = categoriesTimeDao.getAllTimesRequests()
            Log.e("TIME2", test.toString())
        }
    }

    private fun persistRandomMeal(list:List<Meal>){
        GlobalScope.launch(Dispatchers.IO){
            randomMealDao.deleteAllRandomMeal()
            randomMealDao.insert(list)

            timeRandomMealDao.upsert(TimeRandomMealRequest(Date().time))
            val test = categoriesTimeDao.getAllTimesRequests()
            Log.e("TIME2", test.toString())
        }
    }

    private suspend fun featchCategory(){
        dataSource.featchCategories()
    }

    private suspend fun featchRandomMeal(){
        dataSource.featchMealRandom()
    }

    private fun fetchIsNeeded(now:Date, wait:Int):Boolean{
        val after = Date(now.time + wait * HOUR)
        return Date(Date().time).after(after)
    }
}