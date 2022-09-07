package com.example.core.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.BuildConfig
import com.example.core.databinding.ItemRowListBinding
import com.example.core.domain.model.Favourite

class FavouriteAdapter(private val onItemClickCallback: OnItemClickCallback) :
    ListAdapter<Favourite, FavouriteAdapter.FavouriteViewHolder>(FavouriteDiffCallback) {
    inner class FavouriteViewHolder(var binding: ItemRowListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let { favourite ->
            with(holder.binding) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_POSTER_ORIGINAL + favourite.posterPath)
                    .into(ivPoster)

                ratingBar.rating = ((favourite.voteAverage / 10) * 5).toFloat()

                tvTitle.text = favourite.title
                tvRating.text = String.format("%.1f", favourite.voteAverage.toFloat())

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(favourite)
                }

                btnDetail.setOnClickListener {
                    onItemClickCallback.onItemClicked(favourite)
                }

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(favourite: Favourite)
    }
}