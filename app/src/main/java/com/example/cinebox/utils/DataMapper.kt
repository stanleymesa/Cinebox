package com.example.cinebox.utils

import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.remote.response.MovieResponse
import com.example.cinebox.core.data.source.remote.response.MoviesItem
import com.example.cinebox.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(moviesItem: List<MoviesItem>): List<NowPlayingMovieEntity> =
        moviesItem.map {
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

    fun mapEntityToDomain(nowPlayingMovieEntity: NowPlayingMovieEntity): Movie =
        Movie(
            id = nowPlayingMovieEntity.id,
            overview = nowPlayingMovieEntity.overview,
            title = nowPlayingMovieEntity.title,
            genre = nowPlayingMovieEntity.genre,
            posterPath = nowPlayingMovieEntity.posterPath,
            backdropPath = nowPlayingMovieEntity.backdropPath,
            releaseDate = nowPlayingMovieEntity.releaseDate,
            popularity = nowPlayingMovieEntity.popularity,
            voteAverage = nowPlayingMovieEntity.voteAverage
        )

}