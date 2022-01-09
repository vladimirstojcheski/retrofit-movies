package com.example.retrofit_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.retrofit_movies.database.AppDatabase
import com.example.retrofit_movies.databinding.FragmentSecondBinding
import com.example.retrofit_movies.model.Movie
import com.example.retrofit_movies.viewmodel.SecondFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as MainActivity
        val selectedMovieTitle = activity.getMovieTitle()
        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        secondFragmentViewModel.loadMovie(selectedMovieTitle)
        var selectedMovie: Movie? = secondFragmentViewModel.getMovie()
        val database = AppDatabase.getInstance(activity)
        CoroutineScope(Dispatchers.IO).launch {
            selectedMovie = database.MovieDao().getMovie(selectedMovieTitle)[0]
        }

        CoroutineScope(Dispatchers.Main).launch {

            val txtTitle = view.findViewById<TextView>(R.id.txtViewTitleId)
            val txtReleased = view.findViewById<TextView>(R.id.txtViewReleaseId)
            val txtImdb = view.findViewById<TextView>(R.id.txtViewIMDBId)
            val txtActors = view.findViewById<TextView>(R.id.txtViewActorsId)
            val txtAwards = view.findViewById<TextView>(R.id.txtViewAwardsId)
            val txtPlot = view.findViewById<TextView>(R.id.txtViewPlotId)

            txtTitle.text = selectedMovie?.title
            txtReleased.text = selectedMovie?.released
            txtImdb.text = selectedMovie?.imdbRating
            txtActors.text = selectedMovie?.actors
            txtAwards.text = selectedMovie?.awards
            txtPlot.text = selectedMovie?.plot
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}