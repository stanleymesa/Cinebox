package com.example.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.FavouritePageUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val favouritePageUseCase: FavouritePageUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> {
                FavouriteViewModel(favouritePageUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}