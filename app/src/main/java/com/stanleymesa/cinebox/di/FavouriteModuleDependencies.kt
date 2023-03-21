package com.stanleymesa.cinebox.di

import com.stanleymesa.core.domain.usecase.FavouritePageUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavouriteModuleDependencies {

    fun favouritePageUseCase(): FavouritePageUseCase

}