package com.example.cinebox.core.data.source.remote.network

import com.example.cinebox.BuildConfig
import com.example.cinebox.core.data.source.remote.response.GenreResponse
import com.example.cinebox.core.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): Response<GenreResponse>

}