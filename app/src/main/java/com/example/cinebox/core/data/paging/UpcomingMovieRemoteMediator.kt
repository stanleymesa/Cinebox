package com.example.cinebox.core.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.cinebox.core.data.source.local.entity.NowPlayingRemoteKeys
import com.example.cinebox.core.data.source.local.entity.UpcomingMovieEntity
import com.example.cinebox.core.data.source.local.entity.UpcomingRemoteKeys
import com.example.cinebox.core.data.source.local.room.MovieDatabase
import com.example.cinebox.core.data.source.remote.network.ApiService
import com.example.cinebox.utils.DataMapper

@OptIn(ExperimentalPagingApi::class)
class UpcomingMovieRemoteMediator(
    private val apiService: ApiService,
    private val database: MovieDatabase,
) : RemoteMediator<Int, UpcomingMovieEntity>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingMovieEntity>,
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH ->{
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val responseData = apiService.getUpcomingMovie(page = page).body()!!
            val endOfPaginationReached = responseData.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteUpcomingRemoteKeys()
                    database.movieDao().deleteAllUpcomingMovie()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.results.map {
                    UpcomingRemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAllUpcomingRemoteKeys(keys)
                database.movieDao()
                    .insertUpcomingMovie(DataMapper.mapResponsesToUpcomingEntities(responseData.results))
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UpcomingMovieEntity>): UpcomingRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getUpcomingRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UpcomingMovieEntity>): UpcomingRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getUpcomingRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UpcomingMovieEntity>): UpcomingRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getUpcomingRemoteKeysId(id)
            }
        }
    }


}