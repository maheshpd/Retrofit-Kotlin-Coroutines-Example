package com.createsapp.retrofit_kotlin_coroutines_example.data.api

import com.createsapp.retrofit_kotlin_coroutines_example.data.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}