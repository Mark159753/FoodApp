package com.example.foodapp

import android.app.Application
import com.example.foodapp.di.components.AppComponent
import com.example.foodapp.di.components.DaggerAppComponent
import com.example.foodapp.di.modules.AppModule

class FoodApp:Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent() = appComponent
}