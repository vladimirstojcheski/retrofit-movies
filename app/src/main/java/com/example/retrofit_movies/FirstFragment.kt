package com.example.retrofit_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_movies.adapter.MovieAdapter
import com.example.retrofit_movies.api.OmdbApi
import com.example.retrofit_movies.api.OmdbApiClient
import com.example.retrofit_movies.database.AppDatabase
import com.example.retrofit_movies.databinding.FragmentFirstBinding
import com.example.retrofit_movies.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var omdbApiClient: OmdbApi
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter
    //private var database: AppDatabase = AppDatabase.getInstance(view?.context!!)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        omdbApiClient = OmdbApiClient.getOmdbApi()!!

        val movieTitle = view.findViewById<EditText>(R.id.etTitle)

        //val database = AppDatabase.getInstance(view.context)

        movieRecyclerView = view.findViewById(R.id.allMoviesRecyclerView)

        movieRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = MovieAdapter(mutableListOf())

        movieRecyclerView.setHasFixedSize(true)

        movieRecyclerView.adapter = recyclerViewAdapter

        movieTitle.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                val movieTitleEt: String = movieTitle.text.toString()
                searchMovieByTitle(movieTitleEt)
                CoroutineScope(Dispatchers.IO).launch {

                }
                true
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }




//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun searchMovieByTitle(movieTitle: String) {
        omdbApiClient.getMovieByTitle(movieTitle, 59864573, "short").enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>) {
                displayData(response.body())
                saveMovieInDatabase(response.body())
                Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable) {
                var m = t.message
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayData(movie: Movie) {
        recyclerViewAdapter.updateData(movie)
    }

    private fun saveMovieInDatabase(movie: Movie)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val activity = activity as MainActivity
            val database: AppDatabase = AppDatabase.getInstance(activity)
            database.MovieDao().insertMovie(movie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}