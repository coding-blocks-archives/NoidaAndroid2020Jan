package com.puldroid.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author aggarwalpulkit596
 */
class TodoAdapter(val list: ArrayList<String>) :
    RecyclerView.Adapter<ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_expandable_list_item_1,parent,false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(s: String) {
            if(itemView is TextView){
                itemView.text = s
            }
    }
}
