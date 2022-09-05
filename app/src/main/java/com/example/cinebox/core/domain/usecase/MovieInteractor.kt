package com.example.cinebox.core.domain.usecase

import androidx.paging.PagingData
import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.domain.model.Cast
import com.example.cinebox.core.domain.model.Detail
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.core.domain.model.Movie
import com.example.cinebox.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    HomePageUseCase, DetailPageUseCase, FavouritePageUseCase, SearchPageUseCase {
    override fun getAllNowPlayingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getAllNowPlayingMovie()

    override fun getAllUpcomingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getAllUpcomingMovie()

    override fun getAllTopRatedMovie(): Flow<PagingData<Movie>> =
        movieRepository.getAllTopRatedMovie()

    override fun getDetailMovie(id: String): Flow<Resource<Detail>> =
        movieRepository.getDetailMovie(id)

    override fun getCreditMovie(id: String): Flow<Resource<Cast>> =
        movieRepository.getCreditMovie(id)

    override fun isFavourite(id: String): Flow<Boolean> =
        movieRepository.isFavourite(id)

    override fun insertFavourite(favouriteMovie: Favourite) =
        movieRepository.insertFavourite(favouriteMovie)

    override fun deleteFavouriteById(id: String) =
        movieRepository.deleteFavouriteById(id)

    override fun getAllFavourite(): Flow<Resource<List<Favourite>>> =
        movieRepository.getAllFavourite()

    override fun getSearchMovie(query: String): Flow<PagingData<Movie>> =
        movieRepository.getSearchMovie(query)

}