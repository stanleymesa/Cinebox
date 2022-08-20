package com.example.cinebox.core.data

import androidx.paging.PagingData
import androidx.paging.map
import com.example.cinebox.core.data.source.local.LocalDataSource
import com.example.cinebox.core.data.source.remote.RemoteDataSource
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.repository.IMovieRepository
import com.example.cinebox.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
                DataMapper.mapEntityToDomain(it)
            }
        }
}