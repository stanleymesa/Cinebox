package com.example.cinebox.core.domain.repository

import androidx.paging.PagingData
import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.data.ResourceList
import com.example.cinebox.core.domain.model.Cast
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllNowPlayingMovie(): Flow<PagingData<Movie>>
    fun getAllUpcomingMovie(): Flow<PagingData<Movie>>
    fun getAllTopRatedMovie(): Flow<PagingData<Movie>>
    fun getDetailMovie(id: String): Flow<Resource<Detail>>
    fun getCreditMovie(id: String): Flow<Resource<Cast>>
    fun isFavourite(id: String): Flow<Boolean>
    fun insertFavourite(favouriteMovie: Favourite)
    fun deleteFavouriteById(id: String)
    fun getAllFavourite(): Flow<Resource<List<Favourite>>>
    fun getSearchMovie(query: String): Flow<PagingData<Movie>>
}