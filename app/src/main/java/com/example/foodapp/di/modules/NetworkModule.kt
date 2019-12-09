package com.example.foodapp.di.modules

import com.example.foodapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService():ApiService{
        return ApiService.invoke()
    }
}