package com.tksko.mymovies.ui.movies_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.tksko.mymovies.databinding.FragmentMoviesHomeBinding
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.ui.movies_home.adapter.MoviesAdapter
import com.tksko.mymovies.ui.movies_home.adapter.MoviesLoadStateAdapter
import com.tksko.mymovies.ui.movies_home.viewmodel.MoviesViewModel
import com.tksko.mymovies.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesHomeFragment : Fragment() {

    private val binding by viewBinding {
        FragmentMoviesHomeBinding.inflate(layoutInflater)
    }

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(::onMoviesItemClick)
    }

    private val moviesLoadStateAdapter: MoviesLoadStateAdapter by lazy {
        MoviesLoadStateAdapter(::retryMoviesPageLoad)
    }

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLists()
    }

    private fun setupLists() {
        binding.apply {
            listMovies.adapter = moviesAdapter.withLoadStateFooter(moviesLoadStateAdapter)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    moviesViewModel.moviesList.collect {
                        moviesAdapter.submitData(it)
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    moviesAdapter.loadStateFlow.collect {
                        moviesLoadStateAdapter.loadState = it.refresh
                    }
                }
            }
        }
    }

    private fun retryMoviesPageLoad() = moviesAdapter.retry()

    private fun onMoviesItemClick(item: MovieResult) {
        findNavController().navigate(
            MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailsFragment(
                item
            )
        )
    }
}