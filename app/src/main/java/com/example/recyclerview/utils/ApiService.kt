package com.example.recyclerview.utils

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")  // Fetch all products
    fun getProducts(): Call<List<Product>>
}
