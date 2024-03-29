package com.example.foodapp.data.network

import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.Meal
import com.example.foodapp.data.model.MealsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.themealdb.com/api/json/v1/"
const val API_KEY = "1/"

interface ApiService {

    @GET("random.php")
    suspend fun getRandomReceipe():Response<MealsResponse>

    @GET("categories.php")
    suspend fun getCategories():Response<CategoriesResponse>

    @GET("search.php")
    suspend fun searchRequest(@Query("s") request:String):Response<MealsResponse>

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id:String):Response<MealsResponse>

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") category:String):Response<MealsResponse>

    companion object{
        operator fun invoke():ApiService{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL + API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okkHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}