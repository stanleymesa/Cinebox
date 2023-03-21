package com.stanleymesa.cinebox.presentation.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stanleymesa.core.domain.model.Movie
import com.stanleymesa.core.domain.usecase.SearchPageUseCase
import com.stanleymesa.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchPageUseCase: SearchPageUseCase) :
    ViewModel() {

    fun getSearchMovie(query: String): LiveData<Event<PagingData<Movie>>> =
        searchPageUseCase.getSearchMovie(query).asLiveData().map {
            Event(it)
        }

}