package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favourite(

    val id: String,

    val title: String,

    val posterPath: String? = "",

    val voteAverage: Double,

    ) : Parcelable
