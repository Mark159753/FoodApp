package com.example.foodapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.foodapp.data.db.dao.CategoryDao
import com.example.foodapp.data.db.dao.RandomMealDao
import com.example.foodapp.data.db.dao.TimeRequestDao
import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Category
import com.example.foodapp.data.model.Meal
import com.example.foodapp.data.model.TimeRequest
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
    private val timeRequestDao: TimeRequestDao
): MainRepository {

    init {
        dataSource.apply {
            downloadMealRandom.observeForever{

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private suspend fun initCategory(){
        val lastRequests = timeRequestDao.getAllTimesRequests()

        if (lastRequests == null){
            val time = Date().time
            timeRequestDao.upsert(TimeRequest(1, time, time))
            featchCategory()
            return
        }

//        val test = timeRequestDao.getAllTimesRequests()
//        Log.e("TIME1", Date(test.lastTimeRandomMeal!!).toString())
//        Log.e("TIME2", Date(test.lastTimeCategory!!).toString())

        if (fetchIsNeeded(Date(lastRequests.lastTimeCategory!!), 3)){
            featchCategory()
        }
    }

    private fun persistCategory(category:CategoriesResponse){
        GlobalScope.launch(Dispatchers.IO){
            categoryDao.deleteAllCategories()
            categoryDao.insert(category.categories)

            timeRequestDao.updateCategoryTime(Date().time)
            val test = timeRequestDao.getAllTimesRequests()
            Log.e("TIME2", test.toString())
        }
    }

    private suspend fun featchCategory(){
        dataSource.featchCategories()
    }

    private fun fetchIsNeeded(now:Date, wait:Int):Boolean{
        val before = Date(Date().time + wait * HOUR)
        return now.after(before)
    }
}