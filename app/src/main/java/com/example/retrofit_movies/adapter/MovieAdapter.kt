package com.example.retrofit_movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_movies.R
import com.example.retrofit_movies.model.Movie

class MovieAdapter (var allMovies: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val moviePicture: ImageView
        val movieTitle: TextView
        val movieYear: TextView

        init {
            moviePicture = view.findViewById(R.id.moviePicture)
            movieTitle = view.findViewById(R.id.movieTitle)
            movieYear = view.findViewById(R.id.movieYear)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = allMovies[position]

        holder.movieTitle.text = currentMovie.title
        holder.movieYear.text = currentMovie.year
        Glide.with(holder.itemView).load(currentMovie.poster).into(holder.moviePicture)
    }

    override fun getItemCount(): Int {
        return allMovies.size
    }

    fun updateData(movie: Movie) {
        this.allMovies.add(movie)
        this.notifyDataSetChanged()
    }
}