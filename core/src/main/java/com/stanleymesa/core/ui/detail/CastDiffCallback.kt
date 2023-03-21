package com.stanleymesa.core.ui.detail

import androidx.recyclerview.widget.DiffUtil
import com.stanleymesa.core.data.source.remote.response.CastItem

object CastDiffCallback: DiffUtil.ItemCallback<CastItem>() {
    override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
        return oldItem == newItem
    }
}