package com.example.foodapp.di.modules

import com.example.foodapp.data.network.ApiService
import com.example.foodapp.data.network.response.MealDataSource
import com.example.foodapp.data.network.response.MealDetailDataSource
import com.example.foodapp.data.network.response.SearchDataSourceImpl
import com.example.foodapp.ui.details.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService():ApiService{
        return ApiService.invoke()
    }

}