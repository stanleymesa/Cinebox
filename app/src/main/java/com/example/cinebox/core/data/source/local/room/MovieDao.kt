package com.example.cinebox.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.local.entity.TopRatedMovieEntity
import com.example.cinebox.core.data.source.local.entity.UpcomingMovieEntity

@Dao
interface MovieDao {

    // Now Playing Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovie(movie: List<NowPlayingMovieEntity>)

    @Query("SELECT * FROM now_playing_movie")
    fun getAllNowPlayingMovie(): PagingSource<Int, NowPlayingMovieEntity>

    @Query("DELETE FROM now_playing_movie")
    suspend fun deleteAllNowPlayingMovie()

    // Upcoming Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(movie: List<UpcomingMovieEntity>)

    @Query("SELECT * FROM upcoming_movie")
    fun getAllUpcomingMovie(): PagingSource<Int, UpcomingMovieEntity>

    @Query("DELETE FROM upcoming_movie")
    suspend fun deleteAllUpcomingMovie()

    // Top Rated Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovie(movie: List<TopRatedMovieEntity>)

    @Query("SELECT * FROM top_rated_movie")
    fun getAllTopRatedMovie(): PagingSource<Int, TopRatedMovieEntity>

    @Query("DELETE FROM top_rated_movie")
    suspend fun deleteAllTopRatedMovie()
}