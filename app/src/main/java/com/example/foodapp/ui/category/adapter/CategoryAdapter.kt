package com.example.foodapp.ui.category.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.squareup.picasso.Picasso

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.Holder>(){

    private var list = emptyList<Meal>()
    private var listener:MealClickListener? = null

    fun setDataList(list: List<Meal>){
        this.list = list
        notifyDataSetChanged()
    }

    fun setLister(listener:MealClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler_item, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.title.text = data.strMeal
        Picasso.get()
            .load(data.strMealThumb)
            .into(holder.img)
    }

    inner class Holder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener{
        val img = view.findViewById<ImageView>(R.id.categoryThumb)
        val title = view.findViewById<TextView>(R.id.category_name)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onMealClick(list[adapterPosition].idMeal)
        }
    }

    interface MealClickListener{
        fun onMealClick(id:String)
    }
}