package com.stanleymesa.favourite.data.usecase

import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.FavouritePageUseCase
import com.stanleymesa.favourite.dummy.Dummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFavouriteUseCase: FavouritePageUseCase {
    override fun getAllFavourite(): Flow<Resource<List<Favourite>>> = flow {
        emit(Resource.Success(arrayListOf(Dummy.favourite())))
    }
}