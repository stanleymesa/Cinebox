package com.example.cinebox.core.domain.model

import android.os.Parcelable
import com.example.cinebox.core.data.source.remote.response.CastItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(

    val cast: List<CastItem>,

    val id: Int,

): Parcelable

