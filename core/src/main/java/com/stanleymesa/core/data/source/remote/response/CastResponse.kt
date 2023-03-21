package com.stanleymesa.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CreditResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem>,

	@field:SerializedName("id")
	val id: Int,

)

@Parcelize
data class CastItem(

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String? = "",

	@field:SerializedName("id")
	val id: Int,

): Parcelable

