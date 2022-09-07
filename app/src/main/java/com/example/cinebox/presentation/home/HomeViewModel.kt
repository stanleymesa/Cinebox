package com.example.cinebox.presentation.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.HomePageUseCase
import com.example.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
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

    lateinit var nowPlayingMovie: LiveData<PagingData<Movie>>

    lateinit var upcomingMovie: LiveData<PagingData<Movie>>

    lateinit var topRatedMovie: LiveData<PagingData<Movie>>

    fun getAllNowPlayingMovie() {
        viewModelScope.launch {
            _isNowPlayingLoading.postValue(Event(true))
            nowPlayingMovie = homePageUseCase.getAllNowPlayingMovie().asLiveData()
        }
    }

    fun getAllUpcomingMovie() {
        viewModelScope.launch {
            _isUpcomingLoading.postValue(Event(true))
            upcomingMovie = homePageUseCase.getAllUpcomingMovie().asLiveData()
        }
    }

    fun getAllTopRatedMovie() {
        viewModelScope.launch {
            _isTopRatedLoading.postValue(Event(true))
            topRatedMovie = homePageUseCase.getAllTopRatedMovie().asLiveData()
        }
    }
}