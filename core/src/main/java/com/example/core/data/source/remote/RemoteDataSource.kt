package com.example.core.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.data.paging.MoviePagingSource
import com.example.core.data.paging.NowPlayingMovieRemoteMediator
import com.example.core.data.paging.TopRatedMovieRemoteMediator
import com.example.core.data.paging.UpcomingMovieRemoteMediator
import com.example.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.core.data.source.local.entity.TopRatedMovieEntity
import com.example.core.data.source.local.entity.UpcomingMovieEntity
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MoviesItem
import com.example.core.domain.model.Cast
import com.example.core.domain.model.Detail
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor (private val apiService: ApiService, private val database: MovieDatabase) {
    private val movieDao = database.movieDao()

    fun getAllNowPlayingMovie(): Flow<PagingData<NowPlayingMovieEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NowPlayingMovieRemoteMediator(apiService,
                database),
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

    suspend fun getDetailMovie(id: String): Flow<Resource<Detail>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getDetailMovie(id)

            if (response.isSuccessful) {
                response.body()?.let { detailResponse ->
                    val detailMovie = DataMapper.mapDetailResponseToDomain(detailResponse)
                    emit(Resource.Success(detailMovie))
                }
            } else {
                emit(Resource.Error("Error retrieving data"))
            }

        } catch (ex: Exception) {
            emit(Resource.Error(ex.message.toString()))
        }
    }

    suspend fun getCreditMovie(id: String): Flow<Resource<Cast>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getCreditMovie(id)

            if (response.isSuccessful) {
                response.body()?.let { creditResponse ->
                    val creditMovie = DataMapper.mapCreditResponseToDomain(creditResponse)
                    emit(Resource.Success(creditMovie))
                }
            } else {
                emit(Resource.Error("Error retrieving data"))
            }

        } catch (ex: Exception) {
            emit(Resource.Error(ex.message.toString()))
        }
    }

    fun getSearchMovie(query: String): Flow<PagingData<MoviesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, query)
            }
        ).flow
    }

}