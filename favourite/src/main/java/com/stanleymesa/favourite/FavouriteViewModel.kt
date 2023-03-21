package com.stanleymesa.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.FavouritePageUseCase
import com.stanleymesa.core.utils.Event

class FavouriteViewModel (private val favouritePageUseCase: FavouritePageUseCase) :
    ViewModel() {

    fun getAllFavourite(): LiveData<Event<Resource<List<Favourite>>>> =
        favouritePageUseCase.getAllFavourite().asLiveData().map {
            Event(it)
        }
}