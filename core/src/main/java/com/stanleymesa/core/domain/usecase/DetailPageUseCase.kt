package com.stanleymesa.core.domain.usecase

import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface DetailPageUseCase {
    fun getDetailMovie(id: String): Flow<Resource<Detail>>
    fun getCreditMovie(id: String): Flow<Resource<Cast>>
    fun isFavourite(id: String): Flow<Boolean>
    fun insertFavourite(favouriteMovie: Favourite)
    fun deleteFavouriteById(id: String)
}