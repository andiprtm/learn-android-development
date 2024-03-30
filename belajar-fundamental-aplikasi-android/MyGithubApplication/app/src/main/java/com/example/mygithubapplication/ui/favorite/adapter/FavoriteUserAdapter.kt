package com.example.mygithubapplication.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubapplication.data.favorite.entity.UserEntity
import com.example.mygithubapplication.databinding.ItemRowUserBinding

class FavoriteUserAdapter(private val onItemClickCallback: (UserEntity) -> Unit) :
    ListAdapter<UserEntity, FavoriteUserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserEntity, onItemClickCallback: (UserEntity) -> Unit) {

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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}