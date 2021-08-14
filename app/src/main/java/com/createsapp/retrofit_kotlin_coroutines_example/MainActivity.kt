package com.createsapp.retrofit_kotlin_coroutines_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.createsapp.retrofit_kotlin_coroutines_example.data.User
import com.createsapp.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.createsapp.retrofit_kotlin_coroutines_example.data.api.RetrofitBuilder
import com.createsapp.retrofit_kotlin_coroutines_example.ui.MainAdapter
import com.createsapp.retrofit_kotlin_coroutines_example.ui.MainViewModel
import com.createsapp.retrofit_kotlin_coroutines_example.ui.ViewModelFactory
import com.createsapp.retrofit_kotlin_coroutines_example.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.util.List.of

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper((RetrofitBuilder.apiService)))).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data.let { users -> retrieveList(users) }
                    }

                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }

                }
            }
        })
    }

    private fun retrieveList(users: List<User>?) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}