package com.example.cinebox.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.Favourite
import com.example.core.domain.usecase.FavouritePageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val favouritePageUseCase: FavouritePageUseCase) :
    ViewModel() {

        fun getAllFavourite(): LiveData<Resource<List<Favourite>>> = favouritePageUseCase.getAllFavourite().asLiveData()
}