package com.stanleymesa.core.di

import com.stanleymesa.core.data.MovieRepository
import com.stanleymesa.core.domain.repository.IMovieRepository
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