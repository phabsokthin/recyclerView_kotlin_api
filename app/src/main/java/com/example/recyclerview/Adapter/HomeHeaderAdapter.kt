package com.example.recyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Model.HomeItem
import com.example.recyclerview.R

class HomeHeaderAdapter(
    private val context: Context,
    private val items: List<HomeItem>, // Pass a single list instead of three separate lists
    private val onHeaderClick: (String) -> Unit
) : RecyclerView.Adapter<HomeHeaderAdapter.HomeHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeaderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_header_item, parent, false)
        return HomeHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeHeaderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class HomeHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imgHomeHeader)
        private val titleText: TextView = itemView.findViewById(R.id.textHomeTitle)
        private val descriptionText: TextView = itemView.findViewById(R.id.textHomeDes)

        fun bind(item: HomeItem) {
            imageView.setImageResource(item.image)
            titleText.text = item.title
            descriptionText.text = item.description

            itemView.setOnClickListener {
                onHeaderClick(item.title)
            }
        }
    }
}
