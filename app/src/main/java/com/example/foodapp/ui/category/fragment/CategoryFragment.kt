package com.example.foodapp.ui.category.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.FoodApp

import com.example.foodapp.R
import com.example.foodapp.data.model.Category
import com.example.foodapp.ui.BaseViewModelFactory
import com.example.foodapp.ui.category.adapter.CategoryAdapter
import com.example.foodapp.ui.details.DetailsActivity
import com.example.foodapp.ui.main.adapter.RecyclerViewCategoriesAdapter
import com.example.foodapp.untils.Actions
import com.example.foodapp.untils.SpaceItemDecorator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

private const val ARG_PARAM1 = "CATEGORY_DATA"


class CategoryFragment : Fragment(), CategoryAdapter.MealClickListener {
    private var category: Category? = null
    private lateinit var recyclerAdapter:CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    @Inject lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel: CategoryFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getParcelable(ARG_PARAM1)
        }
        (activity?.application as? FoodApp)?.getAppComponent()
            ?.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoryFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val img = view.findViewById<ImageView>(R.id.category_img)
        val description = view.findViewById<TextView>(R.id.category_meal)
        recyclerView = view.findViewById(R.id.category_recycler)
        description.text = category?.strCategoryDescription
        Picasso.get()
            .load(category?.strCategoryThumb)
            .into(img)
        recyclerAdapter = CategoryAdapter()
        recyclerAdapter.setLister(this)
        initData()
    }

    private fun initData(){
        viewModel.categoryRequest(category!!.strCategory)
        viewModel.categoriesMeal.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            category_parogress_bar.visibility = View.GONE
            recyclerAdapter.setDataList(it)
            recyclerView.apply {
                adapter = recyclerAdapter
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(activity!!.applicationContext, 2, GridLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = true
                addItemDecoration(SpaceItemDecorator(5))
            }
        })
    }

    override fun onMealClick(id: String) {
        val intent = Intent(activity!!.applicationContext, DetailsActivity::class.java)
        intent.action = Actions.NEED_SERVER_REQUEST
        intent.putExtra(Actions.DETAIL_MEAL, id)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(category: Category) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, category)
                }
            }
    }
}
