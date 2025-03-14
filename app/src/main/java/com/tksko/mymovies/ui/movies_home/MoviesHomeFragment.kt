package com.tksko.mymovies.ui.movies_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.tksko.mymovies.R
import com.tksko.mymovies.databinding.FragmentMoviesHomeBinding
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.ui.movies_home.adapter.MoviesAdapter
import com.tksko.mymovies.ui.movies_home.adapter.MoviesLoadStateAdapter
import com.tksko.mymovies.ui.movies_home.adapter.SearchMoviesAdapter
import com.tksko.mymovies.ui.movies_home.viewmodel.MoviesViewModel
import com.tksko.mymovies.utils.hide
import com.tksko.mymovies.utils.setScrollBehavior
import com.tksko.mymovies.utils.show
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

    private val searchAdapter: SearchMoviesAdapter by lazy {
        SearchMoviesAdapter(::onMoviesItemClick)
    }

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        setupLists()
        setupEnterAnimation()
    }

    private fun setupEnterAnimation() {
        postponeEnterTransition()
        binding.apply {
            listMovies.doOnPreDraw { startPostponedEnterTransition() }
            listSearch.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun setupLists() {
        binding.apply {
            listMovies.adapter = moviesAdapter.withLoadStateFooter(moviesLoadStateAdapter)
            listSearch.adapter = searchAdapter
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
            moviesViewModel.searchResultsLiveData.observe(
                viewLifecycleOwner,
                Observer(::onSearchResults)
            )
        }
    }

    private fun retryMoviesPageLoad() = moviesAdapter.retry()

    private fun onMoviesItemClick(item: MovieResult, target: View) {
        val extras = FragmentNavigatorExtras(
            target to target.transitionName
        )
        findNavController().navigate(
            MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailsFragment(
                item,
                target.transitionName
            ),
            extras
        )
    }

    private fun setupSearch() {
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search, menu)
                (menu.findItem(R.id.action_search)?.actionView as? SearchView)?.apply {
                    maxWidth = Integer.MAX_VALUE
                    setOnQueryTextListener(searchQueryListener)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem) = true
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private val searchQueryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = true

        override fun onQueryTextChange(newText: String?): Boolean {
            if (!newText.isNullOrBlank()) {
                moviesViewModel.searchMovies(newText)
            } else {
                onSearchResults()
            }
            return true
        }
    }

    private fun onSearchResults(newResults: List<MovieResult> = emptyList()) = binding.apply {
        if (newResults.isNotEmpty()) {
            searchAdapter.submitList(newResults)
            listSearch.show()
            toolbar.setScrollBehavior(isScrollable = false)
        } else {
            listSearch.hide()
            toolbar.setScrollBehavior(isScrollable = true)
        }
    }
}