
package com.stanleymesa.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.stanleymesa.core.data.source.local.entity.TopRatedMovieEntity
import com.stanleymesa.core.data.source.local.entity.TopRatedRemoteKeys
import com.stanleymesa.core.data.source.local.room.MovieDatabase
import com.stanleymesa.core.data.source.remote.network.ApiService
import com.stanleymesa.core.utils.DataMapper

@OptIn(ExperimentalPagingApi::class)
class TopRatedMovieRemoteMediator(
    private val apiService: ApiService,
    private val database: MovieDatabase,
) : RemoteMediator<Int, TopRatedMovieEntity>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedMovieEntity>,
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
            val responseData = apiService.getTopRatedMovie(page = page).body()!!
            val endOfPaginationReached = responseData.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteTopRatedRemoteKeys()
                    database.movieDao().deleteAllTopRatedMovie()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.results.map {
                    TopRatedRemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAllTopRatedRemoteKeys(keys)
                database.movieDao()
                    .insertTopRatedMovie(DataMapper.mapResponsesToTopRatedEntities(responseData.results))

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TopRatedMovieEntity>): TopRatedRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getTopRatedRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TopRatedMovieEntity>): TopRatedRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getTopRatedRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TopRatedMovieEntity>): TopRatedRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getTopRatedRemoteKeysId(id)
            }
        }
    }


}