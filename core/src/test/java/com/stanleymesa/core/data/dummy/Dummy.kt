package com.stanleymesa.cinebox.data.dummy

import com.stanleymesa.core.data.source.local.entity.FavouriteEntity
import com.stanleymesa.core.data.source.remote.response.*
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite

object Dummy {

    fun moviesItem(): MoviesItem = MoviesItem(
        id = 1,
        overview = "This is Iron Man",
        title = "Iron Man",
        genreIds = arrayListOf(1, 2, 3),
        posterPath = "poster.jpg",
        backdropPath = "backdrop.jpg",
        releaseDate = "28-07-2019",
        popularity = 1000.0,
        voteAverage = 80.0
    )

    fun movieResponse(): MovieResponse = MovieResponse(
        page = 1,
        results = arrayListOf(moviesItem())
    )

    fun detailResponse(): DetailResponse = DetailResponse(
        title = "Iron Man",
        backdropPath = "backdrop.jpg",
        genres = arrayListOf(
            GenresItem(
                id = 1,
                name = "Action"
            )
        ),
        popularity = 1000.0,
        id = 1,
        overview = "This is Iron Man",
        posterPath = "poster.jpg",
        productionCompanies = arrayListOf(
            ProductionCompaniesItem(
                logoPath = "logo.jpg",
                name = "Marvel",
                id = 1,
                originCountry = "en"
            )
        ),
        releaseDate = "28-07-2019",
        voteAverage = 8.0
    )

    fun creditResponse(): CreditResponse = CreditResponse(
        id = 1,
        cast = arrayListOf(
            CastItem(
                character = "Iron Man",
                name = "Robert Downey Jr",
                profilePath = "profile.jpg",
                id = 1
            )
        )
    )

    fun favouriteEntity(): FavouriteEntity = FavouriteEntity(
        id = "1",
        title = "Iron Man",
        posterPath = "poster.jpg",
        voteAverage = 8.0
    )

    fun favourite(): Favourite = Favourite(
        id = "1",
        title = "Iron Man",
        posterPath = "poster.jpg",
        voteAverage = 8.0
    )

}