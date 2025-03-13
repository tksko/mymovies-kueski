package com.tksko.mymovies.ui.movies_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tksko.mymovies.databinding.FragmentMoviesHomeBinding
import com.tksko.mymovies.ui.movies_home.viewmodel.MoviesViewModel
import com.tksko.mymovies.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesHomeFragment : Fragment() {

    private val binding by viewBinding {
        FragmentMoviesHomeBinding.inflate(layoutInflater)
    }

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}