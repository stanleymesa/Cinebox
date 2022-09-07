package com.example.cinebox.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Cast
import com.example.core.domain.model.Detail
import com.example.core.domain.model.Favourite
import com.example.core.domain.usecase.DetailPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailPageUseCase: DetailPageUseCase) :
    ViewModel() {

    fun getDetailMovie(id: String): LiveData<com.example.core.data.Resource<Detail>> =
        detailPageUseCase.getDetailMovie(id).asLiveData()

    fun getCreditMovie(id: String): LiveData<com.example.core.data.Resource<Cast>> =
        detailPageUseCase.getCreditMovie(id).asLiveData()

    fun isFavourite(id: String): LiveData<Boolean> = detailPageUseCase.isFavourite(id).asLiveData()

    fun insertFavourite(favouriteMovie: Favourite) =
        detailPageUseCase.insertFavourite(favouriteMovie)

    fun deleteFavouriteById(id: String) = detailPageUseCase.deleteFavouriteById(id)
}