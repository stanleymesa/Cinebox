package com.example.cinebox.utils

import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.remote.response.MovieResponse
import com.example.cinebox.core.data.source.remote.response.MoviesItem

object DataMapper {

    fun mapResponsesToEntities(moviesItem: List<MoviesItem>): List<NowPlayingMovieEntity> {
        return moviesItem.map {
            NowPlayingMovieEntity(
                id = it.id,
                overview = it.overview,
                title = it.title,
                genre = it.genreIds.toString(),
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
    }

}