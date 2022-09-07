package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface HomePageUseCase {
    fun getAllNowPlayingMovie(): Flow<PagingData<Movie>>
    fun getAllUpcomingMovie(): Flow<PagingData<Movie>>
    fun getAllTopRatedMovie(): Flow<PagingData<Movie>>
}