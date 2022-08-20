package com.example.cinebox.core.domain.usecase

import androidx.paging.PagingData
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): HomePageUseCase {
    override fun getAllNowPlayingMovie(): Flow<PagingData<Movie>> = movieRepository.getAllNowPlayingMovie()
}