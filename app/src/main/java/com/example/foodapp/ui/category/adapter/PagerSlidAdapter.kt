package com.example.foodapp.ui.category.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.foodapp.data.model.Category
import com.example.foodapp.ui.category.fragment.CategoryFragment

class PagerSlidAdapter(fragmentManager: FragmentManager,
                       private val list: List<Category>):FragmentStatePagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].strCategory
    }
}