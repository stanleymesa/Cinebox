package com.example.cinebox.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.usecase.SearchPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchPageUseCase: SearchPageUseCase) :
    ViewModel() {

    fun getSearchMovie(query: String): LiveData<PagingData<Movie>> =
        searchPageUseCase.getSearchMovie(query).asLiveData().cachedIn(viewModelScope)

}