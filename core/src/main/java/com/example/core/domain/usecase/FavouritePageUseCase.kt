package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouritePageUseCase {
    fun getAllFavourite(): Flow<Resource<List<Favourite>>>
}