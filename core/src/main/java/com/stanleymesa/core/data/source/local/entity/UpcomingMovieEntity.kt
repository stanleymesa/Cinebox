package com.stanleymesa.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movie")
data class UpcomingMovieEntity(

    @PrimaryKey
    val id: String,

    val overview: String,

    val title: String,

    val genre: String,

    val posterPath: String?,

    val backdropPath: String?,

    val releaseDate: String?,

    val popularity: Double,

    val voteAverage: Double

)



