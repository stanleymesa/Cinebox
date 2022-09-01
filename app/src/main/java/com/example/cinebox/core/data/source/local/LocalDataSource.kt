package com.example.cinebox.core.data.source.local

import com.example.cinebox.core.data.source.local.room.MovieDao
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun isFavourite(id: String): Flow<Boolean> = movieDao.isFavourite(id)

    suspend fun insertFavourite(favouriteMovie: Favourite) =
        movieDao.insertFavourite(DataMapper.mapFavouriteDomainToEntity(favouriteMovie))

    suspend fun deleteFavouriteById(id: String) = movieDao.deleteFavouriteById(id)

}