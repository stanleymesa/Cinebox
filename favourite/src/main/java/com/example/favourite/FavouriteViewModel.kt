package com.example.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.Favourite
import com.example.core.domain.usecase.FavouritePageUseCase

class FavouriteViewModel (private val favouritePageUseCase: FavouritePageUseCase) :
    ViewModel() {

    fun getAllFavourite(): LiveData<Resource<List<Favourite>>> = favouritePageUseCase.getAllFavourite().asLiveData()
}