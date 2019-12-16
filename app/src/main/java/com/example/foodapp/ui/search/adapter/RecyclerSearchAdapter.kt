package com.example.foodapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.model.Meal
import com.squareup.picasso.Picasso

class RecyclerSearchAdapter: RecyclerView.Adapter<RecyclerSearchAdapter.Holder>() {

    private var listener:OnSearchItemClickListener? = null
    private var list = emptyList<Meal>()

    fun setListData(list: List<Meal>?){
        notifyDataSetChanged()
        this.list = list ?: emptyList()
    }

    fun setClickListener(listener:OnSearchItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listItem = list[position]
        holder.apply {
            title.text = listItem.strMeal
            instruction.text = listItem.strInstructions
        }
        Picasso.get()
            .load(listItem.strMealThumb)
            .into(holder.img)
    }

    inner class Holder(view:View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.search_title)
        val instruction = view.findViewById<TextView>(R.id.search_instruction)
        val img = view.findViewById<ImageView>(R.id.search_img)

        init {
            view.setOnClickListener { listener?.onItemClick(adapterPosition, list[adapterPosition]) }
        }
    }

    interface OnSearchItemClickListener{
        fun onItemClick(pos:Int, itemMeal:Meal)
    }
}