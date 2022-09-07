package com.example.core.domain.model

import android.os.Parcelable
import com.example.core.data.source.remote.response.CastItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(

    val cast: List<CastItem>,

    val id: Int,

    ): Parcelable

