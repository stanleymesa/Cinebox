package com.example.cinebox.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
data class GenreEntity(

    @PrimaryKey
    val id: Int,

    val name: String

)



