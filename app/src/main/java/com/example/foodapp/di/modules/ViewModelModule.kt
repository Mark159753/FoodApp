package com.example.foodapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.di.ViewModelKey
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.category.CategoryViewModel
import com.example.foodapp.ui.category.fragment.CategoryFragmentViewModel
import com.example.foodapp.ui.details.DetailViewModel
import com.example.foodapp.ui.main.MainViewModel
import com.example.foodapp.ui.search.SearchViewModel
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
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(model: SearchViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(model: DetailViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(model: CategoryViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryFragmentViewModel::class)
    abstract fun bindCategoryFragmentViewModel(model: CategoryFragmentViewModel):ViewModel

    @Binds
    abstract fun bindMainFactory(factory: BaseViewModelFactory):ViewModelProvider.Factory
}