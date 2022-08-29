package com.example.cinebox.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val id: String,

    val overview: String,

    val title: String,

    val genre: String,

    val posterPath: String? = "",

    val backdropPath: String? = "",

    val releaseDate: String,

    val popularity: Double,

    val voteAverage: Double

): Parcelable