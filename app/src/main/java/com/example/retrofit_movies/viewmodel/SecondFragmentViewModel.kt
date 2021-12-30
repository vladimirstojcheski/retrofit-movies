package com.example.retrofit_movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.retrofit_movies.database.AppDatabase
import com.example.retrofit_movies.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(application: Application): AndroidViewModel(application){

    private var app: Application = application

    private val database: AppDatabase = AppDatabase.getInstance(application)

    private var movie: Movie? = null

    fun loadMovie(movieTitle: String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val movieTmp = database.MovieDao().getMovie(movieTitle)

            withContext(Dispatchers.Main){
                movie = movieTmp[0]
            }
        }
    }

    fun getMovie(): Movie?{
        return movie
    }

}