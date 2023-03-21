package com.stanleymesa.cinebox.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.DetailPageUseCase
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