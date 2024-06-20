package com.moove.mooveapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moove.mooveapp.R
import com.moove.mooveapp.databinding.ItemReviewBinding
import com.moove.mooveapp.view.home.review.ReviewUserItem

class ReviewUserAdapter(private val reviews: List<ReviewUserItem>) :
    RecyclerView.Adapter<ReviewUserAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReviewBinding.bind(itemView)
        fun bind(review: ReviewUserItem) {
            binding.imgReview.setImageResource(review.imageResource)
            binding.tvTitleReview.text = review.title
            binding.tvDescReview.text = review.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}
