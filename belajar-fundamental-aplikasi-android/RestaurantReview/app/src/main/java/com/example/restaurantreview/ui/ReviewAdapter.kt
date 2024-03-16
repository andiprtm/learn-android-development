package com.example.restaurantreview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantreview.R
import com.example.restaurantreview.data.response.CustomerReviewsItem
import com.example.restaurantreview.databinding.ItemReviewBinding
import com.example.restaurantreview.ui.ReviewAdapter.MyViewHolder

class ReviewAdapter : ListAdapter<CustomerReviewsItem, MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context : Context, review: CustomerReviewsItem){
            binding.tvItem.text =
                context.getString(R.string.review_text, review.review, review.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(holder.itemView.context, review)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomerReviewsItem>(){
            override fun areItemsTheSame(
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}