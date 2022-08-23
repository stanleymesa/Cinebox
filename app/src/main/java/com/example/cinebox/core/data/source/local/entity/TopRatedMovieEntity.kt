package com.example.cinebox.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "top_rated_movie")
data class TopRatedMovieEntity(

    @PrimaryKey
    val id: String,

    val overview: String,

    val title: String,

    val genre: String,

    val posterPath: String?,

    val backdropPath: String?,

    val releaseDate: String,

    val popularity: Double,

    val voteAverage: Double

)



