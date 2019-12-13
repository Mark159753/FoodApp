package com.example.foodapp.di.components

import com.example.foodapp.di.modules.*
import com.example.foodapp.ui.main.MainActivity
import com.example.foodapp.ui.search.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, DataSourceModule::class,
DataBaseModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(main:MainActivity)

    fun inject(main:SearchActivity)

    @Component.Builder
    interface Builder{

        fun build():AppComponent

        fun appModule(app:AppModule):Builder
    }
}