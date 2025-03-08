package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.Adapter.HomeAdapter
import com.example.recyclerview.Model.HomeItem
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.utils.ApiClient
import com.example.recyclerview.utils.PostApiClient
import com.example.recyclerview.utils.PostModel
import com.example.recyclerview.utils.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: ActivityMainBinding
    private val productList = mutableListOf<Product>() // Store products from API
    private val postList= mutableListOf<PostModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        fetchProducts() // Fetch products from API
        fetchPost()
    }

    private fun initRecyclerView() {
        // Header List
        val homeHeaderList = listOf(
            HomeItem(R.drawable.baseline_8k_plus_24, "Header 13", "desc 1"),
            HomeItem(R.drawable.ic_launcher_foreground, "Header 2", "desc 2"),
            HomeItem(R.drawable.baseline_access_time_filled_24, "Header 3", "desc 3")
        )

        // Body List
//        val homeBodyList = listOf(
//            HomeItem(R.drawable.baseline_8k_plus_24, "Body 1", "descbody 1"),
//            HomeItem(R.drawable.ic_launcher_foreground, "Body 2", "descbody 2"),
//            HomeItem(R.drawable.baseline_access_time_filled_24, "Body 3", "descbody 3")
//        )

        // Initialize Adapter (Initially empty product list)
        homeAdapter = HomeAdapter(this, homeHeaderList,productList, postList)

        // Set Layout Manager & Adapter
        binding.rvBodyRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvBodyRecycler.adapter = homeAdapter
    }



    private fun fetchProducts() {
        ApiClient.apiService.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        productList.clear()
                        productList.addAll(it)
                        homeAdapter.notifyItemChanged(1) // Refresh the product section
                    }
                } else {
                    showToast("Failed to load products: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                showToast("Failed to fetch products: ${t.message}")
            }
        })
    }

    //fetch post

    private fun fetchPost(){
        PostApiClient.postApiService.getPosts().enqueue(object : Callback<List<PostModel>>{
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        postList.clear()
                        postList.addAll(it.take(10)) //take limit data only 10
                        homeAdapter.notifyItemChanged(1)
                    }
                }
                else{
                    showToast("Failed to load ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                showToast("Failed fet post ${t.message}")
            }
        })


    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
