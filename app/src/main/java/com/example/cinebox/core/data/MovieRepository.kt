package com.example.cinebox.core.data

import androidx.paging.PagingData
import androidx.paging.map
import com.example.cinebox.core.data.source.local.LocalDataSource
import com.example.cinebox.core.data.source.remote.RemoteDataSource
import com.example.cinebox.core.domain.model.Cast
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.repository.IMovieRepository
import com.example.cinebox.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val coroutineScope: CoroutineScope,
) : IMovieRepository {

    override fun getAllNowPlayingMovie(): Flow<PagingData<Movie>> =
        remoteDataSource.getAllNowPlayingMovie().map { pagingData ->
            pagingData.map {
                DataMapper.mapNowPlayingEntityToDomain(it)
            }
        }

    override fun getAllUpcomingMovie(): Flow<PagingData<Movie>> =
        remoteDataSource.getAllUpcomingMovie().map { pagingData ->
            pagingData.map {
                DataMapper.mapUpcomingEntityToDomain(it)
            }
        }

    override fun getAllTopRatedMovie(): Flow<PagingData<Movie>> =
        remoteDataSource.getAllTopRatedMovie().map { pagingData ->
            pagingData.map {
                DataMapper.mapTopRatedEntityToDomain(it)
            }
        }

    override fun getDetailMovie(id: String): Flow<Resource<Detail>> =
        flow {
            emitAll(remoteDataSource.getDetailMovie(id))
        }.flowOn(Dispatchers.IO)

    override fun getCreditMovie(id: String): Flow<Resource<Cast>> =
        flow {
            emitAll(remoteDataSource.getCreditMovie(id))
        }.flowOn(Dispatchers.IO)

    override fun isFavourite(id: String): Flow<Boolean> = localDataSource.isFavourite(id)

    override fun insertFavourite(favouriteMovie: Favourite) {
        coroutineScope.launch {
            localDataSource.insertFavourite(favouriteMovie)
        }
    }

    override fun deleteFavouriteById(id: String) {
        coroutineScope.launch {
            localDataSource.deleteFavouriteById(id)
        }
    }

    override fun getAllFavourite(): Flow<Resource<List<Favourite>>> =
        localDataSource.getAllFavourite()

}