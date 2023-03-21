package com.stanleymesa.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.stanleymesa.core.data.source.local.entity.NowPlayingMovieEntity
import com.stanleymesa.core.data.source.local.entity.NowPlayingRemoteKeys
import com.stanleymesa.core.data.source.local.room.MovieDatabase
import com.stanleymesa.core.data.source.remote.network.ApiService
import com.stanleymesa.core.utils.DataMapper

@OptIn(ExperimentalPagingApi::class)
class NowPlayingMovieRemoteMediator(
    private val apiService: ApiService,
    private val database: MovieDatabase,
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
            val responseData = apiService.getNowPlayingMovie(page = page).body()!!

            val endOfPaginationReached = responseData.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteNowPlayingRemoteKeys()
                    database.movieDao().deleteAllNowPlayingMovie()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1


                val keys = responseData.results.map {
                    NowPlayingRemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAllNowPlayingRemoteKeys(keys)

                database.movieDao()
                    .insertNowPlayingMovie(DataMapper.mapResponsesToNowPlayingEntities(responseData.results))
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, NowPlayingMovieEntity>): NowPlayingRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getNowPlayingRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, NowPlayingMovieEntity>): NowPlayingRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getNowPlayingRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, NowPlayingMovieEntity>): NowPlayingRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getNowPlayingRemoteKeysId(id)
            }
        }
    }


}