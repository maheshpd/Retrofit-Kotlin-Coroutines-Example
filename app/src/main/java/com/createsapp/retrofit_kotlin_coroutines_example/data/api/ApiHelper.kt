package com.createsapp.retrofit_kotlin_coroutines_example.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}