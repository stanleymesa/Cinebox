package com.stanleymesa.cinebox.data.usecase

import androidx.paging.PagingData
import com.stanleymesa.core.domain.model.Movie
import com.stanleymesa.core.domain.usecase.SearchPageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSearchUseCase: SearchPageUseCase {
    override fun getSearchMovie(query: String): Flow<PagingData<Movie>> = flow {
        emit(PagingData.empty())
    }
}