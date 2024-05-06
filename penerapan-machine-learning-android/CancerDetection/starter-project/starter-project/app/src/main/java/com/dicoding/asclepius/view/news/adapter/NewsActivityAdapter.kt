package com.dicoding.asclepius.view.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ItemRowNewsBinding

class NewsActivityAdapter(private val onItemClickCallback: (ArticlesItem?) -> Unit) :
    ListAdapter<ArticlesItem?, NewsActivityAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(private val binding: ItemRowNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticlesItem?, onItemClickCallback: (ArticlesItem?) -> Unit){
            with(binding){
                Glide.with(root.context)
                    .load(item?.urlToImage)
                    .into(imgNews)
                tvNewsTitle.text = item?.title
                tvNewsDescription.text = item?.description
                root.setOnClickListener{
                    onItemClickCallback(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news, onItemClickCallback)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem?>() {

            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}