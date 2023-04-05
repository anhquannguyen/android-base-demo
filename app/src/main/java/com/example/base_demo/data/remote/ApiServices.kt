package com.example.base_demo.data.remote

import com.example.base_demo.data.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("users")
    suspend fun getUsers(@Query("size") size: Int = 10): Users



}