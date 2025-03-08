package com.example.recyclerview.utils

import retrofit2.Call
import retrofit2.http.GET

interface ApiPostService {
    @GET("posts")
    fun getPosts(): Call<List<PostModel>>
}