package com.example.cinebox.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovie(movie: List<NowPlayingMovieEntity>)

    @Query("SELECT * FROM now_playing_movie")
    fun getAllNowPlayingMovie(): PagingSource<Int, NowPlayingMovieEntity>

    @Query("DELETE FROM now_playing_movie")
    suspend fun deleteAllNowPlayingMovie()
}