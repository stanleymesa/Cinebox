package com.example.cinebox.core.domain.usecase

import androidx.paging.PagingData
import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.domain.model.Cast
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchPageUseCase {
    fun getSearchMovie(query: String): Flow<PagingData<Movie>>
}