package com.stanleymesa.core.data.source.local

import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.data.source.local.room.MovieDao
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun isFavourite(id: String): Flow<Boolean> =
        movieDao.isFavourite(id)

    suspend fun insertFavourite(favouriteMovie: Favourite) =
        movieDao.insertFavourite(DataMapper.mapFavouriteDomainToEntity(favouriteMovie))

    suspend fun deleteFavouriteById(id: String) =
        movieDao.deleteFavouriteById(id)

    fun getAllFavourite(): Flow<Resource<List<Favourite>>> =
        movieDao.getAllFavourite().map { data ->
            if (data.isEmpty()) Resource.Empty(null) else
                Resource.Success(DataMapper.mapFavouriteEntitiesToDomains(data))
        }

}