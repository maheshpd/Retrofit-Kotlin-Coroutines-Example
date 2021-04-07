package com.createsapp.retrofit_kotlin_coroutines_example.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.createsapp.retrofit_kotlin_coroutines_example.R
import com.createsapp.retrofit_kotlin_coroutines_example.data.User
import kotlinx.android.synthetic.main.item.view.*

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.userName
                textViewUserEmail.text = user.userEmail
                Glide.with(imageViewAvatar.context)
                    .load(user.image)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int  = users.size

    fun addUsers(users: List<User>?) {
        this.users.apply {
            clear()
            addAll(users!!)
        }
    }


}