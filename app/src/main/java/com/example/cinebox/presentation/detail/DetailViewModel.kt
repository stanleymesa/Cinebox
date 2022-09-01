package com.example.cinebox.presentation.detail

import androidx.lifecycle.*
import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.domain.model.Cast
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.core.domain.usecase.DetailPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailPageUseCase: DetailPageUseCase) :
    ViewModel() {

    fun getDetailMovie(id: String): LiveData<Resource<Detail>> =
        detailPageUseCase.getDetailMovie(id).asLiveData()

    fun getCreditMovie(id: String): LiveData<Resource<Cast>> =
        detailPageUseCase.getCreditMovie(id).asLiveData()

    fun isFavourite(id: String): LiveData<Boolean> = detailPageUseCase.isFavourite(id).asLiveData()

    fun insertFavourite(favouriteMovie: Favourite) =
        detailPageUseCase.insertFavourite(favouriteMovie)

    fun deleteFavouriteById(id: String) = detailPageUseCase.deleteFavouriteById(id)
}