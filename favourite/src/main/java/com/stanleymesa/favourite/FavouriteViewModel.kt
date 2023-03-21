package com.stanleymesa.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.FavouritePageUseCase

class FavouriteViewModel (private val favouritePageUseCase: FavouritePageUseCase) :
    ViewModel() {

    fun getAllFavourite(): LiveData<Resource<List<Favourite>>> = favouritePageUseCase.getAllFavourite().asLiveData()
}