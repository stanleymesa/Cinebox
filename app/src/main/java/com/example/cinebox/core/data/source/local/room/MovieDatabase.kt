package com.example.cinebox.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.local.entity.NowPlayingRemoteKeys
import com.example.cinebox.core.data.source.local.entity.TopRatedMovieEntity
import com.example.cinebox.core.data.source.local.entity.UpcomingMovieEntity

@Database(
    entities = [
        NowPlayingMovieEntity::class,
        UpcomingMovieEntity::class,
        TopRatedMovieEntity::class,
        NowPlayingRemoteKeys::class ],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun nowPlayingRemoteKeysDao(): NowPlayingRemoteKeysDao

}