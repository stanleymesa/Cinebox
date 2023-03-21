package com.stanleymesa.cinebox.data.usecase

import androidx.paging.PagingData
import com.stanleymesa.core.domain.model.Movie
import com.stanleymesa.core.domain.usecase.HomePageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeUseCase: HomePageUseCase {
    override fun getAllNowPlayingMovie(): Flow<PagingData<Movie>> = flow {
        emit(PagingData.empty())
    }

    override fun getAllUpcomingMovie(): Flow<PagingData<Movie>> = flow {
        emit(PagingData.empty())
    }

    override fun getAllTopRatedMovie(): Flow<PagingData<Movie>> = flow {
        emit(PagingData.empty())
    }
}