package com.example.cinebox.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinebox.BuildConfig
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.databinding.ItemRowMovieBinding

class MovieAdapter(private val onItemClickCallback: OnItemClickCallback) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    inner class MovieViewHolder(var binding: ItemRowMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let { movie ->
            with(holder.binding) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_POSTER_W500 + movie.posterPath)
                    .into(ivMovie)

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie.id)
                }
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

}