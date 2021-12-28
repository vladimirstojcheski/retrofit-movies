package com.example.retrofit_movies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OmdbApiClient {

    companion object {
        private var omdbApi: OmdbApi? = null

        fun getOmdbApi(): OmdbApi? {

            if (omdbApi == null) {
                omdbApi = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OmdbApi::class.java)
            }
            return omdbApi
        }

    }
}