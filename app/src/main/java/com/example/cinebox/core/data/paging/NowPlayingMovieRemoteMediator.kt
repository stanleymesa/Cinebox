package com.example.cinebox.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.cinebox.core.data.source.local.entity.NowPlayingMovieEntity
import com.example.cinebox.core.data.source.local.room.MovieDatabase
import com.example.cinebox.core.data.source.remote.network.ApiService
import com.example.cinebox.utils.DataMapper

@OptIn(ExperimentalPagingApi::class)
class NowPlayingMovieRemoteMediator(
    private val apiService: ApiService,
    private val database: MovieDatabase
) : RemoteMediator<Int, NowPlayingMovieEntity>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingMovieEntity>,
    ): MediatorResult {

        val page = INITIAL_PAGE_INDEX

        return try {
            val responseData = apiService.getNowPlayingMovie(page = page).body()!!
            val endOfPaginationReached = responseData.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.movieDao().deleteAllNowPlayingMovie()
                }
                database.movieDao()
                    .insertNowPlayingMovie(DataMapper.mapResponsesToEntities(responseData.results))
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }

    }
}