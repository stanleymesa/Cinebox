package com.example.cinebox.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cinebox.core.data.source.remote.response.CastItem
import com.example.cinebox.core.data.source.remote.response.ProductionCompaniesItem

object ProductionDiffCallback: DiffUtil.ItemCallback<ProductionCompaniesItem>() {
    override fun areItemsTheSame(oldItem: ProductionCompaniesItem, newItem: ProductionCompaniesItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductionCompaniesItem, newItem: ProductionCompaniesItem): Boolean {
        return oldItem == newItem
    }
}