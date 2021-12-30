package com.example.retrofit_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.retrofit_movies.databinding.FragmentSecondBinding
import com.example.retrofit_movies.model.Movie

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


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
        val selectedMovie = activity.getMovie()

        val txtTitle = view.findViewById<TextView>(R.id.txtViewTitleId)
        val txtReleased = view.findViewById<TextView>(R.id.txtViewReleaseId)
        val txtImdb = view.findViewById<TextView>(R.id.txtViewIMDBId)
        val txtActors = view.findViewById<TextView>(R.id.txtViewActorsId)
        val txtAwards = view.findViewById<TextView>(R.id.txtViewAwardsId)
        val txtPlot = view.findViewById<TextView>(R.id.txtViewPlotId)

        txtTitle.text = selectedMovie?.title.toString()
        txtReleased.text = selectedMovie?.released.toString()
        txtImdb.text = selectedMovie?.imdbRating.toString()
        txtActors.text = selectedMovie?.actors.toString()
        txtAwards.text = selectedMovie?.awards.toString()
        txtPlot.text = selectedMovie?.plot.toString()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}