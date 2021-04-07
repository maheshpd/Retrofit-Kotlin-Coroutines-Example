package com.createsapp.retrofit_kotlin_coroutines_example.data.repository

import com.createsapp.retrofit_kotlin_coroutines_example.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}