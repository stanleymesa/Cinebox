package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchPageUseCase {
    fun getSearchMovie(query: String): Flow<PagingData<Movie>>
}