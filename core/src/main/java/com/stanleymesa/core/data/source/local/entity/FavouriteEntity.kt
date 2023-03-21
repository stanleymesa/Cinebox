package com.stanleymesa.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val posterPath: String?,

    val voteAverage: Double

)
