package com.example.cinebox.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinebox.core.data.source.local.entity.*

@Database(
    entities = [
        NowPlayingMovieEntity::class,
        UpcomingMovieEntity::class,
        TopRatedMovieEntity::class,
        NowPlayingRemoteKeys::class,
        UpcomingRemoteKeys::class,
        TopRatedRemoteKeys::class,
        FavouriteEntity::class],
    version = 8,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}