package com.example.practical.api

import com.example.practical.models.MovieDetail
import com.example.practical.models.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApis {

    @GET(".")
    suspend fun getMovieList(
        @Query("apikey") apiKey: String,
        @Query("s") s: String,
        @Query("page") page: Int
    ): Response<MovieList>

    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey: String,
        @Query("i") i: String
    ): Response<MovieDetail>

}
