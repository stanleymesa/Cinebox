package com.example.cinebox.di

import com.example.core.domain.usecase.FavouritePageUseCase
import com.example.core.domain.usecase.MovieInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteModule {

    @Binds
    @Singleton
    abstract fun provideFavouritePageUseCase(movieInteractor: MovieInteractor): FavouritePageUseCase

}