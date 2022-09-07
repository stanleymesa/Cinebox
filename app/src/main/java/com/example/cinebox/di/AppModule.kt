package com.example.cinebox.di

import com.example.core.domain.usecase.*
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

    @Binds
    @ViewModelScoped
    abstract fun provideSearchPageUseCase(movieInteractor: MovieInteractor): SearchPageUseCase

}