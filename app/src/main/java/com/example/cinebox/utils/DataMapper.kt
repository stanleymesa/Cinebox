package com.example.cinebox.utils

import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.local.entity.TopRatedMovieEntity
import com.example.cinebox.core.data.source.local.entity.UpcomingMovieEntity
import com.example.cinebox.core.data.source.remote.response.MoviesItem
import com.example.cinebox.core.domain.model.Movie

object DataMapper {

    // Now Playing

    fun mapResponsesToNowPlayingEntities(moviesItem: List<MoviesItem>): List<NowPlayingMovieEntity> =
        moviesItem.map {
            NowPlayingMovieEntity(
                id = it.id.toString(),
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

    fun mapNowPlayingEntityToDomain(nowPlayingMovieEntity: NowPlayingMovieEntity): Movie =
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

    // Upcoming

    fun mapResponsesToUpcomingEntities(moviesItem: List<MoviesItem>): List<UpcomingMovieEntity> =
        moviesItem.map {
            UpcomingMovieEntity(
                id = it.id.toString(),
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

    fun mapUpcomingEntityToDomain(upcomingMovieEntity: UpcomingMovieEntity): Movie =
        Movie(
            id = upcomingMovieEntity.id,
            overview = upcomingMovieEntity.overview,
            title = upcomingMovieEntity.title,
            genre = upcomingMovieEntity.genre,
            posterPath = upcomingMovieEntity.posterPath,
            backdropPath = upcomingMovieEntity.backdropPath,
            releaseDate = upcomingMovieEntity.releaseDate,
            popularity = upcomingMovieEntity.popularity,
            voteAverage = upcomingMovieEntity.voteAverage
        )

    // Top Rated

    fun mapResponsesToTopRatedEntities(moviesItem: List<MoviesItem>): List<TopRatedMovieEntity> =
        moviesItem.map {
            TopRatedMovieEntity(
                id = it.id.toString(),
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

    fun mapTopRatedEntityToDomain(topRatedMovieEntity: TopRatedMovieEntity): Movie =
        Movie(
            id = topRatedMovieEntity.id,
            overview = topRatedMovieEntity.overview,
            title = topRatedMovieEntity.title,
            genre = topRatedMovieEntity.genre,
            posterPath = topRatedMovieEntity.posterPath,
            backdropPath = topRatedMovieEntity.backdropPath,
            releaseDate = topRatedMovieEntity.releaseDate,
            popularity = topRatedMovieEntity.popularity,
            voteAverage = topRatedMovieEntity.voteAverage
        )

}