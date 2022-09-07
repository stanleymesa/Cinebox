package com.example.core.utils

import com.example.core.data.source.local.entity.FavouriteEntity
import com.example.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.core.data.source.local.entity.TopRatedMovieEntity
import com.example.core.data.source.local.entity.UpcomingMovieEntity
import com.example.core.data.source.remote.response.CreditResponse
import com.example.core.data.source.remote.response.DetailResponse
import com.example.core.data.source.remote.response.MoviesItem
import com.example.core.domain.model.Cast
import com.example.core.domain.model.Detail
import com.example.core.domain.model.Favourite
import com.example.core.domain.model.Movie

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

    // Detail Movie

    fun mapDetailResponseToDomain(detailResponse: DetailResponse): Detail =
        Detail(
            title = detailResponse.title,
            backdropPath = detailResponse.backdropPath,
            genres = detailResponse.genres,
            popularity = detailResponse.popularity,
            id = detailResponse.id,
            overview = detailResponse.overview,
            posterPath = detailResponse.posterPath,
            productionCompanies = detailResponse.productionCompanies,
            releaseDate = detailResponse.releaseDate,
            voteAverage = detailResponse.voteAverage
        )

    // Credit Movie

    fun mapCreditResponseToDomain(creditResponse: CreditResponse): Cast =
        Cast(
            cast = creditResponse.cast,
            id = creditResponse.id
        )

    // Favourite Movie

    fun mapFavouriteDomainToEntity(favourite: Favourite): FavouriteEntity =
        FavouriteEntity(
            id = favourite.id,
            title = favourite.title,
            posterPath = favourite.posterPath,
            voteAverage = favourite.voteAverage
        )

    fun mapFavouriteEntitiesToDomains(favouriteEntities: List<FavouriteEntity>): List<Favourite> =
        favouriteEntities.map {
            Favourite(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )
        }

    fun mapDetailToFavourite(detail: Detail): Favourite =
        Favourite(
            id = detail.id.toString(),
            title = detail.title,
            posterPath = detail.posterPath,
            voteAverage = detail.voteAverage
        )

    fun mapMoviesItemToDomain(moviesItem: MoviesItem): Movie =
        Movie(
            title = moviesItem.title,
            backdropPath = moviesItem.backdropPath,
            popularity = moviesItem.popularity,
            id = moviesItem.id.toString(),
            genre = moviesItem.genreIds.toString(),
            overview = moviesItem.overview,
            posterPath = moviesItem.posterPath,
            releaseDate = moviesItem.releaseDate,
            voteAverage = moviesItem.voteAverage
        )

}