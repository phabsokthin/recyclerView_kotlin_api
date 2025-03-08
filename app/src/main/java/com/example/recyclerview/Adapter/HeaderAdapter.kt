package com.example.recyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class HeaderAdapter(
    private val context: Context,
    private val headers: List<String>,
    private val images: List<Int>,
    //for click if necessary
    private val onHeaderClick: (String) -> Unit

) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.header_item, parent, false)
        return  HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(headers[position], images[position])
    }

    override fun getItemCount(): Int = headers.size

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val textHeader: TextView = itemView.findViewById(R.id.textHeader)

        private val  rvImage: ImageView = itemView.findViewById(R.id.rvImage)

        fun bind(header: String, imageRef: Int){
            textHeader.text = header
            rvImage.setImageResource(imageRef)

            //set click listeneer

            itemView.setOnClickListener {
                onHeaderClick(header)
            }
        }
    }
}