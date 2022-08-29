package com.example.cinebox.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinebox.BuildConfig
import com.example.cinebox.core.data.source.remote.response.ProductionCompaniesItem
import com.example.cinebox.databinding.ItemRowProductionBinding

class ProductionAdapter :
    ListAdapter<ProductionCompaniesItem, ProductionAdapter.ProductionViewHolder>(
        ProductionDiffCallback) {

    inner class ProductionViewHolder(var binding: ItemRowProductionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionViewHolder {
        val binding =
            ItemRowProductionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductionViewHolder, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let { production ->
            with(holder.binding) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_POSTER_W500 + production.logoPath)
                    .into(ivCompany)

                tvCompany.text = production.name
            }
        }
    }

}