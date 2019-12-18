package com.example.foodapp.di.modules

import com.example.foodapp.data.network.response.*
import com.example.foodapp.data.repository.MainRepository
import com.example.foodapp.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNetworkDataSource(par:NetworkDataSourseImpl):NetworkDataSource

    @Binds
    @Singleton
    abstract fun bindMainRepository(par:MainRepositoryImpl):MainRepository

    @Binds
    @Named("SearchDataSourceImpl")
    abstract fun bindSearchDataSource(par:SearchDataSourceImpl):MealDataSource

    @Binds
    @Named("MealDetailDataSource")
    abstract fun bindMealDataSource(par:MealDetailDataSource):MealDataSource

    @Binds
    @Named("CategoryMealDataSourceImpl")
    abstract fun bindCategoryMealDataSource(par:CategoryMealDataSourceImpl):MealDataSource
}