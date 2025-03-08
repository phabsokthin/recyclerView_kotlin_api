package com.example.recyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.utils.Product

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val headerOnclickProduct: (String) -> Unit

) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        fun bind(product: Product) {
            productTitle.text = product.title
            productPrice.text = "$${product.price}"

            // Load image from URL using Glide
            Glide.with(itemView.context)
                .load(product.image)
                .into(productImage)

            itemView.setOnClickListener {
                headerOnclickProduct(product.title)
            }
        }
    }
}
