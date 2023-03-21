package com.stanleymesa.core.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stanleymesa.core.BuildConfig
import com.stanleymesa.core.data.source.remote.response.CastItem
import com.stanleymesa.core.databinding.ItemRowCastBinding

class CastAdapter : ListAdapter<CastItem, CastAdapter.CastViewHolder>(CastDiffCallback) {

    inner class CastViewHolder(var binding: ItemRowCastBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemRowCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let { cast ->
            with(holder.binding) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_POSTER_W500 + cast.profilePath)
                    .into(ivCast)

                tvName.text = cast.name
                tvCharacter.text = cast.character
            }
        }
    }

}