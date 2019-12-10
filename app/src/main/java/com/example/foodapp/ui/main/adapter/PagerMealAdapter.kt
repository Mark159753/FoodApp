package com.example.foodapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.foodapp.data.model.Meal
import com.example.foodapp.ui.main.slider.SlideFragment

class PagerMealAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var list = emptyList<Meal>()

    fun setDataList(list: List<Meal>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return SlideFragment.newInstance(list[position].strMeal!!, list[position].strMealThumb!!)
    }

    override fun getCount(): Int {
        return list.size
    }
}