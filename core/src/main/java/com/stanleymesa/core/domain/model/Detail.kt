package com.stanleymesa.core.domain.model

import android.os.Parcelable
import com.stanleymesa.core.data.source.remote.response.GenresItem
import com.stanleymesa.core.data.source.remote.response.ProductionCompaniesItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(

    val title: String,

    val backdropPath: String? = "",

    val genres: List<GenresItem>,

    val popularity: Double,

    val id: Int,

    val overview: String,

    val posterPath: String? = "",

    val productionCompanies: List<ProductionCompaniesItem>,

    val releaseDate: String? = "",

    val voteAverage: Double,

    ): Parcelable
