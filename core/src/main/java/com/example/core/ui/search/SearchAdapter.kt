package com.example.core.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.BuildConfig
import com.example.core.databinding.ItemRowListBinding
import com.example.core.domain.model.Movie
import com.example.core.ui.home.MovieDiffCallback

class SearchAdapter(private val onItemClickCallback: OnItemClickCallback): PagingDataAdapter<Movie, SearchAdapter.SearchViewModel>(
    MovieDiffCallback) {

    inner class SearchViewModel(var binding: ItemRowListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewModel {
        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewModel(binding)
    }

    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {
        val context = holder.binding.root.context

        getItem(position)?.let { movie ->
            with(holder.binding) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_POSTER_ORIGINAL + movie.posterPath)
                    .into(ivPoster)

                ratingBar.rating = ((movie.voteAverage / 10) * 5).toFloat()

                tvTitle.text = movie.title
                tvRating.text = String.format("%.1f", movie.voteAverage.toFloat())

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }

                btnDetail.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }

            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

}