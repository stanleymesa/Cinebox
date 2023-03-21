package com.stanleymesa.cinebox.presentation.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.stanleymesa.core.domain.model.Movie
import com.stanleymesa.core.domain.usecase.HomePageUseCase
import com.stanleymesa.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homePageUseCase: HomePageUseCase) : ViewModel() {

    private val _isNowPlayingLoading = MutableLiveData<Event<Boolean>>()
    val isNowPlayingLoading: LiveData<Event<Boolean>> = _isNowPlayingLoading

    private val _isUpcomingLoading = MutableLiveData<Event<Boolean>>()
    val isUpcomingLoading: LiveData<Event<Boolean>> = _isUpcomingLoading

    private val _isTopRatedLoading = MutableLiveData<Event<Boolean>>()
    val isTopRatedLoading: LiveData<Event<Boolean>> = _isTopRatedLoading

    private val _nowPlayingMovie = MutableLiveData<Event<PagingData<Movie>>>()
    val nowPlayingMovie: LiveData<Event<PagingData<Movie>>> = _nowPlayingMovie

    private val _upcomingMovie = MutableLiveData<Event<PagingData<Movie>>>()
    val upcomingMovie: LiveData<Event<PagingData<Movie>>> = _upcomingMovie

    private val _topRatedMovie = MutableLiveData<Event<PagingData<Movie>>>()
    val topRatedMovie: LiveData<Event<PagingData<Movie>>> = _topRatedMovie

    fun getAllNowPlayingMovie() = viewModelScope.launch(Dispatchers.IO) {
        _isNowPlayingLoading.postValue(Event(true))
        homePageUseCase.getAllNowPlayingMovie().collect {
            _nowPlayingMovie.postValue(Event(it))
        }
    }

    fun getAllUpcomingMovie() = viewModelScope.launch(Dispatchers.IO) {
        _isUpcomingLoading.postValue(Event(true))
        homePageUseCase.getAllUpcomingMovie().collect {
            _upcomingMovie.postValue(Event(it))
        }
    }

    fun getAllTopRatedMovie() = viewModelScope.launch(Dispatchers.IO) {
        _isTopRatedLoading.postValue(Event(true))
        homePageUseCase.getAllTopRatedMovie().collect {
            _topRatedMovie.postValue(Event(it))
        }
    }
}