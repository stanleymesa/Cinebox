package com.example.cinebox.core.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cinebox.core.data.paging.NowPlayingMovieRemoteMediator
import com.example.cinebox.core.data.paging.TopRatedMovieRemoteMediator
import com.example.cinebox.core.data.paging.UpcomingMovieRemoteMediator
import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.local.entity.TopRatedMovieEntity
import com.example.cinebox.core.data.source.local.entity.UpcomingMovieEntity
import com.example.cinebox.core.data.source.local.room.MovieDatabase
import com.example.cinebox.core.data.source.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor (private val apiService: ApiService, private val database: MovieDatabase) {
    private val movieDao = database.movieDao()

    fun getAllNowPlayingMovie(): Flow<PagingData<NowPlayingMovieEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NowPlayingMovieRemoteMediator(apiService, database),
            pagingSourceFactory = {
                movieDao.getAllNowPlayingMovie()
            }
        ).flow
    }

    fun getAllUpcomingMovie(): Flow<PagingData<UpcomingMovieEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UpcomingMovieRemoteMediator(apiService, database),
            pagingSourceFactory = {
                movieDao.getAllUpcomingMovie()
            }
        ).flow
    }

    fun getAllTopRatedMovie(): Flow<PagingData<TopRatedMovieEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TopRatedMovieRemoteMediator(apiService, database),
            pagingSourceFactory = {
                movieDao.getAllTopRatedMovie()
            }
        ).flow
    }
}