package com.example.recyclerview.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val postApiService: ApiPostService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPostService::class.java)
    }
}