package com.example.retrofit_movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofit_movies.model.Movie
import com.example.retrofit_movies.database.dao.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun MovieDao(): MovieDao

    companion object {

        private  var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance!!
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movie.db"
            ).build()
        }
    }
}