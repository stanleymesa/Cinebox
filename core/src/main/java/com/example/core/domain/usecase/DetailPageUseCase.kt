package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Cast
import com.example.core.domain.model.Detail
import com.example.core.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface DetailPageUseCase {
    fun getDetailMovie(id: String): Flow<Resource<Detail>>
    fun getCreditMovie(id: String): Flow<Resource<Cast>>
    fun isFavourite(id: String): Flow<Boolean>
    fun insertFavourite(favouriteMovie: Favourite)
    fun deleteFavouriteById(id: String)
}