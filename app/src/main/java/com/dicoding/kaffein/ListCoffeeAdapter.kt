package com.dicoding.kaffein

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kaffein.CoffeeDetailActivity.Companion.EXTRA_COFFEE

class ListCoffeeAdapter(private val listcoffee: ArrayList<Coffee>) : RecyclerView.Adapter<ListCoffeeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Coffee)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_coffee_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_coffee_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_coffee_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.new_item_layout, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listcoffee[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, CoffeeDetailActivity::class.java)
            intentDetail.putExtra(EXTRA_COFFEE, listcoffee[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
    override fun getItemCount(): Int = listcoffee.size
}