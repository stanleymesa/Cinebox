package com.example.cinebox.di

import com.example.cinebox.core.domain.usecase.DetailPageUseCase
import com.example.cinebox.core.domain.usecase.FavouritePageUseCase
import com.example.cinebox.core.domain.usecase.HomePageUseCase
import com.example.cinebox.core.domain.usecase.MovieInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideHomePageUseCase(movieInteractor: MovieInteractor): HomePageUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideDetailPageUseCase(movieInteractor: MovieInteractor): DetailPageUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideFavouritePageUseCase(movieInteractor: MovieInteractor): FavouritePageUseCase

}