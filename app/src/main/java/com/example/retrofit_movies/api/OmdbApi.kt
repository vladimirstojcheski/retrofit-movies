package com.example.retrofit_movies.api

import com.example.retrofit_movies.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun getMovieByTitle(@Query("t") title: String,
                        @Query("apikey") key: Long,
                        @Query("plot") plot: String): Call<Movie>
}