package com.example.cinebox.di

import com.example.core.domain.usecase.FavouritePageUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavouriteModuleDependencies {

    fun favouritePageUseCase(): FavouritePageUseCase

}