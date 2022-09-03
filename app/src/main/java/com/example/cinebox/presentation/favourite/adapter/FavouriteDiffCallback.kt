package com.example.cinebox.presentation.favourite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cinebox.core.domain.model.Favourite

object FavouriteDiffCallback: DiffUtil.ItemCallback<Favourite>() {
    override fun areItemsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
        return oldItem == newItem
    }
}