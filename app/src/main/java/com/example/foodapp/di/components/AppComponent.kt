package com.example.foodapp.di.components

import com.example.foodapp.di.modules.AppModule
import com.example.foodapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(main:MainActivity)

    @Component.Builder
    interface Builder{

        fun build():AppComponent

        fun appModule(app:AppModule):Builder
    }
}