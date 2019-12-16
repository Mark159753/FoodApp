package com.example.foodapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.google.android.material.shape.RoundedCornerTreatment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.random_meal_slide.view.*

class PagerRandomMealAdapter(private val context: Context):PagerAdapter() {

    private var listener:OnRandomMealClickListener? = null
    private var list = emptyList<Meal>()

    fun setDataList(list: List<Meal>){
        this.list = list
        notifyDataSetChanged()
    }

    fun setListenr(listener:OnRandomMealClickListener){
        this.listener = listener
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val v = LayoutInflater.from(context).inflate(R.layout.random_meal_slide, container, false)
        val img = v.findViewById<ImageView>(R.id.meal_img_slider)
        val text = v.findViewById<TextView>(R.id.meal_text_slider)

        Picasso.get()
            .load(list[position].strMealThumb)
            .into(img)
        text.text = list[position].strMeal

        v.setOnClickListener { this.listener?.onRandomClick(position, list[position]) }

        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    interface OnRandomMealClickListener{

        fun onRandomClick(pos:Int, item:Meal)
    }
}