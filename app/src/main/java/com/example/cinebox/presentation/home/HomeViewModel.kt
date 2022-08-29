package com.example.cinebox.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.usecase.HomePageUseCase
import com.example.cinebox.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homePageUseCase: HomePageUseCase) : ViewModel() {

//    private val _isNowPlayingLoading = MutableLiveData<Event<Boolean>>()
//    val isNowPlayingLoading: LiveData<Event<Boolean>> = _isNowPlayingLoading

    fun getAllNowPlayingMovie(): LiveData<PagingData<Movie>> =
        homePageUseCase.getAllNowPlayingMovie().asLiveData()

    fun getAllUpcomingMovie(): LiveData<PagingData<Movie>> =
        homePageUseCase.getAllUpcomingMovie().asLiveData()

    fun getAllTopRatedMovie(): LiveData<PagingData<Movie>> =
        homePageUseCase.getAllTopRatedMovie().asLiveData()
}