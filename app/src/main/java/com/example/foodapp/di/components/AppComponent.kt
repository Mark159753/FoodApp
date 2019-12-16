package com.example.foodapp.di.components

import com.example.foodapp.di.modules.*
import com.example.foodapp.ui.category.CategoryActivity
import com.example.foodapp.ui.category.CategoryViewModel
import com.example.foodapp.ui.category.fragment.CategoryFragment
import com.example.foodapp.ui.details.DetailsActivity
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

    fun inject(main:DetailsActivity)

    fun inject(main:CategoryActivity)

    fun inject(main:CategoryFragment)

    @Component.Builder
    interface Builder{

        fun build():AppComponent

        fun appModule(app:AppModule):Builder
    }
}