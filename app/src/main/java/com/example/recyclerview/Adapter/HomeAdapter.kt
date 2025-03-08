package com.example.recyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Holder.BaseViewHolder
import com.example.recyclerview.Model.HomeItem
import com.example.recyclerview.R
import com.example.recyclerview.utils.PostModel
import com.example.recyclerview.utils.Product

class HomeAdapter(
    private val context: Context,
    private val headerList: List<HomeItem>,
    private val productList: List<Product>,
    private val bodyList: List<PostModel>


) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val TYPE_HOME_HEADER = 0
        private const val TYPE_HOME_PRODUCT = 1
        private const val TYPE_HOME_BODY = 2
    }

//    private val productList = mutableListOf<Product>() // Product list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_HOME_HEADER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.home_header_recycler, parent, false)
                HomeHeaderViewHolder(view)
            }

            TYPE_HOME_PRODUCT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_product_recycler, parent, false)
                ProductViewHolder(view)
            }

            TYPE_HOME_BODY -> {
                val view = LayoutInflater.from(context).inflate(R.layout.home_body_item, parent, false)
                HomeBodyViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun getItemCount(): Int {
        //1+ 1 ដោយសារតែយើងមាន header horizon 2 if add header horizon one more we must be add 1 + 1 + 1
        return 1 + 1+ bodyList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
           return TYPE_HOME_HEADER
        }
        else if (position == 1){
            return  TYPE_HOME_PRODUCT
        }
        else {
            return  TYPE_HOME_BODY
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (position == 0) {
            (holder as HomeHeaderViewHolder).bind(headerList)
        }
        else if (position ==1){
            (holder as ProductViewHolder).bind(productList)
        }

        else {

            //posistion 2 ដោយសារតែយើងមាន header horizon 2 if add header horizon one more we must be add postition - 3
            val item = bodyList[position - 2]
            (holder as HomeBodyViewHolder).bind(item)
        }
    }

    inner class HomeHeaderViewHolder(itemView: View) : BaseViewHolder<List<HomeItem>>(itemView) {
        private val rvHomeHeader: RecyclerView = itemView.findViewById(R.id.home_header_rv)

        override fun bind(item: List<HomeItem>) {
            val homeHeaderAdapter = HomeHeaderAdapter(context, item){
                selectHeader -> Toast.makeText(context, "You click header $selectHeader", Toast.LENGTH_SHORT).show()
            }
            rvHomeHeader.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvHomeHeader.adapter = homeHeaderAdapter
        }
    }

    //product
    inner class ProductViewHolder(itemView: View) : BaseViewHolder<List<Product>>(itemView){
        private val productRv: RecyclerView = itemView.findViewById(R.id.item_product_rv)
        override fun bind(item: List<Product>) {
            val  homeProductAdapter = ProductAdapter(context, item){
                //for click product
                selectProduct -> Toast.makeText(context, "You were click product $selectProduct", Toast.LENGTH_SHORT).show()
            }
            productRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            productRv.adapter = homeProductAdapter

        }
    }


    inner class HomeBodyViewHolder(itemView: View) : BaseViewHolder<PostModel>(itemView) {
//        private val imageBody: ImageView = itemView.findViewById(R.id.imgHomeBody)
        private val textBodyTil: TextView = itemView.findViewById(R.id.textBodyTitle)
        private val textBodyDesc: TextView = itemView.findViewById(R.id.textBodyDes)

        override fun bind(item: PostModel) {
//            imageBody.setImageResource(item.image)
            textBodyTil.text = item.title
            textBodyDesc.text = item.body


            itemView.setOnClickListener {
                Toast.makeText(context, "You click item ${item.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }



}