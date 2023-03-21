package com.stanleymesa.cinebox.di

import com.stanleymesa.core.domain.usecase.FavouritePageUseCase
import com.stanleymesa.core.domain.usecase.MovieInteractor
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