package com.example.foodapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.di.ViewModelKey
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(model: MainViewModel):ViewModel

    @Binds
    abstract fun bindMainFactory(factory: BaseViewModelFactory):ViewModelProvider.Factory
}