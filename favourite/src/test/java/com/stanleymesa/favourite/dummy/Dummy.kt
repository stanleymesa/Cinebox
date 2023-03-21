package com.stanleymesa.favourite.dummy

import com.stanleymesa.core.domain.model.Favourite

object Dummy {

    fun favourite(): Favourite = Favourite(
        id = "1",
        title = "Iron Man",
        posterPath = "poster.jpg",
        voteAverage = 8.0
    )

}