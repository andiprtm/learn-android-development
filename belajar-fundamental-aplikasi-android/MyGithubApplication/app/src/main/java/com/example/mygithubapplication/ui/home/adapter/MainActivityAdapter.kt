package com.example.mygithubapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubapplication.data.remote.response.ItemsItem
import com.example.mygithubapplication.databinding.ItemRowUserBinding

class MainActivityAdapter(private val onItemClickCallback: (ItemsItem) -> Unit) :
    ListAdapter<ItemsItem, MainActivityAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ItemsItem, onItemClickCallback: (ItemsItem) -> Unit) {

            with(binding) {
                Glide.with(root.context)
                    .load(user.avatarUrl)
                    .into(imgUserAvatar)
                tvName.text = user.login
                tvUrl.text = user.htmlUrl
                root.setOnClickListener {
                    onItemClickCallback(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, onItemClickCallback)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}