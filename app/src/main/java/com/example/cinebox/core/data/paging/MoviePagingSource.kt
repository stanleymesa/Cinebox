package com.example.cinebox.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinebox.core.data.source.remote.network.ApiService
import com.example.cinebox.core.data.source.remote.response.MoviesItem

class MoviePagingSource(
    private val apiService: ApiService,
    private val query: String) :
    PagingSource<Int, MoviesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getSearchMovie(query = query, page = position)
            val responseBody = responseData.body()!!

            LoadResult.Page(
                data = responseBody.results,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseBody.results.isEmpty()) null else position + 1
            )

        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}