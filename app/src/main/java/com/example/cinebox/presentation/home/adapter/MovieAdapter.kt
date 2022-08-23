package com.example.cinebox.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinebox.BuildConfig
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.databinding.ItemRowMovieBinding

class MovieAdapter: PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    inner class MovieViewHolder(var binding: ItemRowMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let {
            Glide.with(context)
                .load(BuildConfig.BASE_URL_POSTER + it.posterPath)
                .into(holder.binding.ivMovie)
        }
    }

}