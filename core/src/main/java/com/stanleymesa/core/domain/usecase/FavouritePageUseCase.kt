package com.stanleymesa.core.domain.usecase

import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouritePageUseCase {
    fun getAllFavourite(): Flow<Resource<List<Favourite>>>
}