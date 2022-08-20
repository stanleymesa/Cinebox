package com.example.cinebox.core.di

import com.example.cinebox.core.data.MovieRepository
import com.example.cinebox.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

}