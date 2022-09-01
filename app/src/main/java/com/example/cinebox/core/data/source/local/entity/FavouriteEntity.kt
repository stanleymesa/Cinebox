package com.example.cinebox.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinebox.core.data.source.remote.response.GenresItem

@Entity(tableName = "favourite")
data class FavouriteEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val posterPath: String?,

    val voteAverage: Double

)
