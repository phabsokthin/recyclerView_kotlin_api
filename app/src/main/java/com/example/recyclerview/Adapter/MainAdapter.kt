package com.example.recyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Holder.BaseViewHolder
import com.example.recyclerview.R

class MainAdapter(
    private val  context: Context,
    private val  headerList: List<String>,
    private val imageList: List<Int>,  // Added images for headers

    private val  itemList: List<String>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_BODY_ITEM = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
          TYPE_HEADER -> {
              val view = LayoutInflater.from(context).inflate(R.layout.header_recycler, parent, false)
              HeaderViewHolder(view)
          }
            TYPE_BODY_ITEM -> {
                val view = LayoutInflater.from(context).inflate(R.layout.body_items, parent, false)
                ItemBodyViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun getItemCount(): Int {
        return  itemList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return TYPE_HEADER
        }
        else{
             return  TYPE_BODY_ITEM
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
       if (holder is HeaderViewHolder){
           holder.bind(headerList to imageList)
       }
        else if (holder is ItemBodyViewHolder){
            val item = itemList[position -1]
            holder.bind(item)
       }
    }

    //Header ViewHolder

//    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<List<String>>(itemView){
//        private val rvHeaderView: RecyclerView = itemView.findViewById(R.id.rvHeader)
//        override fun bind(item: List<String>) {
//                val headerAdaperHori = HeaderAdapter(context, item)  {selectHeader ->
//
//                    Toast.makeText(context, "You were click: $selectHeader", Toast.LENGTH_SHORT).show()
//                }
//            rvHeaderView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            rvHeaderView.adapter = headerAdaperHori
//
//        }
//    }


    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<Pair<List<String>, List<Int>>>(itemView) {
        private val rvHeaderView: RecyclerView = itemView.findViewById(R.id.rvHeader)

        override fun bind(item: Pair<List<String>, List<Int>>) {
            val (headers, images) = item  // Extract header text and images

            val headerAdapterHori = HeaderAdapter(context, headers, images) { selectedHeader ->
                Toast.makeText(context, "You clicked: $selectedHeader", Toast.LENGTH_SHORT).show()
            }

            rvHeaderView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvHeaderView.adapter = headerAdapterHori
        }
    }

    inner class ItemBodyViewHolder(itemView: View) : BaseViewHolder<String>(itemView){
        private val textBodyItem: TextView = itemView.findViewById(R.id.textBody)
        override fun bind(item: String) {
            textBodyItem.text = item

            //use click lisetener

            itemView.setOnClickListener {
                Toast.makeText(context, "Item you clicked: $item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}