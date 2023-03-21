package com.stanleymesa.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing_remote_keys")
data class NowPlayingRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?,
)
