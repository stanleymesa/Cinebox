package com.stanleymesa.core.data.source.local.room

import androidx.paging.PagingSource
import com.stanleymesa.cinebox.data.dummy.Dummy
import com.stanleymesa.core.data.source.local.entity.FavouriteEntity
import com.stanleymesa.core.data.source.local.entity.NowPlayingMovieEntity
import com.stanleymesa.core.data.source.local.entity.TopRatedMovieEntity
import com.stanleymesa.core.data.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieDao : MovieDao {

    var inserted = false
    var deleted = false

    override suspend fun insertNowPlayingMovie(movie: List<NowPlayingMovieEntity>) {
        TODO("Not yet implemented")
    }

    override fun getAllNowPlayingMovie(): PagingSource<Int, NowPlayingMovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNowPlayingMovie() {
        TODO("Not yet implemented")
    }

    override suspend fun insertUpcomingMovie(movie: List<UpcomingMovieEntity>) {
        TODO("Not yet implemented")
    }

    override fun getAllUpcomingMovie(): PagingSource<Int, UpcomingMovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUpcomingMovie() {
        TODO("Not yet implemented")
    }

    override suspend fun insertTopRatedMovie(movie: List<TopRatedMovieEntity>) {
        TODO("Not yet implemented")
    }

    override fun getAllTopRatedMovie(): PagingSource<Int, TopRatedMovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTopRatedMovie() {
        TODO("Not yet implemented")
    }

    override fun getAllFavourite(): Flow<List<FavouriteEntity>> = flow {
        emit(arrayListOf(Dummy.favouriteEntity()))
    }

    override fun isFavourite(id: String): Flow<Boolean> = flow {
        emit(true)
    }

    override suspend fun insertFavourite(favouriteMovie: FavouriteEntity) {
        inserted = true
    }

    override suspend fun deleteFavouriteById(id: String) {
        deleted = true
    }
}