package com.example.cinebox.core.domain.usecase

import com.example.cinebox.core.data.Resource
import com.example.cinebox.core.data.ResourceList
import com.example.cinebox.core.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouritePageUseCase {
    fun getAllFavourite(): Flow<Resource<List<Favourite>>>
}