package com.example.foodapp.di.modules

import com.example.foodapp.data.network.response.NetworkDataSource
import com.example.foodapp.data.network.response.NetworkDataSourseImpl
import com.example.foodapp.data.repository.MainRepository
import com.example.foodapp.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNetworkDataSource(par:NetworkDataSourseImpl):NetworkDataSource

    @Binds
    @Singleton
    abstract fun bindMainRepository(par:MainRepositoryImpl):MainRepository
}