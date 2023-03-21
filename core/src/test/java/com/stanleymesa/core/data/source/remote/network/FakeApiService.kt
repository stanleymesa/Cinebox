package com.stanleymesa.core.data.source.remote.network

import com.stanleymesa.cinebox.data.dummy.Dummy
import com.stanleymesa.core.data.source.remote.response.CreditResponse
import com.stanleymesa.core.data.source.remote.response.DetailResponse
import com.stanleymesa.core.data.source.remote.response.MovieResponse
import retrofit2.Response

class FakeApiService: ApiService {
    override suspend fun getNowPlayingMovie(key: String, page: Int): Response<MovieResponse> =
        Response.success(Dummy.movieResponse())

    override suspend fun getUpcomingMovie(key: String, page: Int): Response<MovieResponse> =
        Response.success(Dummy.movieResponse())

    override suspend fun getTopRatedMovie(key: String, page: Int): Response<MovieResponse> =
        Response.success(Dummy.movieResponse())

    override suspend fun getDetailMovie(id: String, key: String): Response<DetailResponse> =
        Response.success(Dummy.detailResponse())

    override suspend fun getCreditMovie(id: String, key: String): Response<CreditResponse> =
        Response.success(Dummy.creditResponse())

    override suspend fun getSearchMovie(
        key: String,
        query: String,
        page: Int,
    ): Response<MovieResponse> =
        Response.success(Dummy.movieResponse())
}