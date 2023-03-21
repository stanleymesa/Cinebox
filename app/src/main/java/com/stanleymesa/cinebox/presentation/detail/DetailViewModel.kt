package com.stanleymesa.cinebox.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.DetailPageUseCase
import com.stanleymesa.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailPageUseCase: DetailPageUseCase) :
    ViewModel() {

    fun getDetailMovie(id: String): LiveData<Event<Resource<Detail>>> =
        detailPageUseCase.getDetailMovie(id).asLiveData().map {
            Event(it)
        }

    fun getCreditMovie(id: String): LiveData<Event<Resource<Cast>>> =
        detailPageUseCase.getCreditMovie(id).asLiveData().map {
            Event(it)
        }

    fun isFavourite(id: String): LiveData<Event<Boolean>> =
        detailPageUseCase.isFavourite(id).asLiveData().map {
            Event(it)
        }

    fun insertFavourite(favouriteMovie: Favourite) =
        detailPageUseCase.insertFavourite(favouriteMovie)

    fun deleteFavouriteById(id: String) = detailPageUseCase.deleteFavouriteById(id)
}