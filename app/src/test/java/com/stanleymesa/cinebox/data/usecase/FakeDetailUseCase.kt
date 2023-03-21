package com.stanleymesa.cinebox.data.usecase

import com.stanleymesa.cinebox.data.dummy.Dummy
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.domain.model.Cast
import com.stanleymesa.core.domain.model.Detail
import com.stanleymesa.core.domain.model.Favourite
import com.stanleymesa.core.domain.usecase.DetailPageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDetailUseCase: DetailPageUseCase {

    var isInserted = false
    var isDeleted = false

    override fun getDetailMovie(id: String): Flow<Resource<Detail>> = flow {
        emit(Resource.Success(Dummy.detail()))
    }

    override fun getCreditMovie(id: String): Flow<Resource<Cast>> = flow {
        emit(Resource.Success(Dummy.cast()))
    }

    override fun isFavourite(id: String): Flow<Boolean> = flow {
        emit(true)
    }

    override fun insertFavourite(favouriteMovie: Favourite) {
        isInserted = true
    }

    override fun deleteFavouriteById(id: String) {
        isDeleted = true
    }
}