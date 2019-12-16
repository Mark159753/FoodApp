package com.example.foodapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categorie_item.view.*

class RecyclerViewCategoriesAdapter: RecyclerView.Adapter<RecyclerViewCategoriesAdapter.Holder>() {

    private var list = emptyList<Category>()
    private var listener:CategoryClickListener? = null

    fun setDateList(list: List<Category>){
        this.list = list
        notifyDataSetChanged()
    }

    fun setListener(listener:CategoryClickListener){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.categoryName.text = list[position].strCategory
        Picasso.get()
            .load(list[position].strCategoryThumb)
            .into(holder.img)
    }

    inner class Holder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener{
        val img = view.findViewById<ImageView>(R.id.category_thumb)
        val categoryName = view.findViewById<TextView>(R.id.category_text)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onCategoryClick(adapterPosition)
        }
    }

    interface CategoryClickListener{
        fun onCategoryClick(position:Int)
    }
}