package com.stanleymesa.cinebox.data.dummy

import com.stanleymesa.core.data.source.remote.response.CastItem
import com.stanleymesa.core.data.source.remote.response.GenresItem
import com.stanleymesa.core.data.source.remote.response.ProductionCompaniesItem
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite

object Dummy {
    fun detail(): Detail = Detail(
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

    fun cast(): Cast = Cast(
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

    fun favourite(): Favourite = Favourite(
        id = "1",
        title = "Iron Man",
        posterPath = "poster.jpg",
        voteAverage = 8.0
    )

}