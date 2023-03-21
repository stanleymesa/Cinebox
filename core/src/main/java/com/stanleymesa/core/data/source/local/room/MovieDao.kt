package com.stanleymesa.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stanleymesa.core.data.source.local.entity.FavouriteEntity
import com.stanleymesa.core.data.source.local.entity.NowPlayingMovieEntity
import com.stanleymesa.core.data.source.local.entity.TopRatedMovieEntity
import com.stanleymesa.core.data.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

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

    // Favourite Movie

    @Query("SELECT * FROM favourite")
    fun getAllFavourite(): Flow<List<FavouriteEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM favourite WHERE id = :id)")
    fun isFavourite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouriteMovie: FavouriteEntity)

    @Query("DELETE FROM favourite WHERE id = :id")
    suspend fun deleteFavouriteById(id: String)
}