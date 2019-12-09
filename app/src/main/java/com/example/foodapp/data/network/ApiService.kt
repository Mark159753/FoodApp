package com.example.foodapp.data.network

import com.example.foodapp.data.model.CategoriesResponse
import com.example.foodapp.data.model.MealsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://www.themealdb.com/api/json/v1/"
const val API_KEY = "1/"

interface ApiService {

    @GET("random.php")
    suspend fun getRandomReceipe():Response<MealsResponse>

    @GET("categories.php")
    suspend fun getCategories():Response<CategoriesResponse>

    companion object{
        operator fun invoke():ApiService{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

//            val requestInterceptor = object :Interceptor{
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val mUrl = chain.request()
//                        .url
//                        .newBuilder()
//                        .addPathSegment(API_KEY)
//                        .build()
//                    val request = chain.request()
//                        .newBuilder()
//                        .url(mUrl)
//                        .build()
//                    return chain.proceed(request)
//                }
//            }
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