package com.example.cinebox.core.data.source.local

import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.data.ResourceList
import com.example.cinebox.core.data.source.local.room.MovieDao
import com.example.cinebox.core.domain.model.Favourite
import com.example.cinebox.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun getAllFavourite(): Flow<ResourceList<Favourite>> = flow {
        emit(ResourceList.Loading())
        try {
            movieDao.getAllFavourite().map {
                if (it.isEmpty()) {
                    emit(ResourceList.Empty())
                } else {
                    emit(ResourceList.Success(DataMapper.mapFavouriteEntitiesToDomains(it)))
                }
            }

        } catch (ex: Exception) {
            emit(ResourceList.Error(ex.message.toString()))
        }
    }

}