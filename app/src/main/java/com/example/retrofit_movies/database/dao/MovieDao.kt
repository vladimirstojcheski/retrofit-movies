package com.example.retrofit_movies.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.retrofit_movies.model.Movie

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: Movie)

}