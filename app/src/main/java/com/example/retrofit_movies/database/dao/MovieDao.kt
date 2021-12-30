package com.example.retrofit_movies.database.dao

import androidx.room.*
import com.example.retrofit_movies.model.Movie

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM Movie WHERE title = :title")
    abstract fun getMovie(title: String): List<Movie>

}