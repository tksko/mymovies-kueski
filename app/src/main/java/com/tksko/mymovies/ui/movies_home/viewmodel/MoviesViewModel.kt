package com.tksko.mymovies.ui.movies_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.domain.usecase.GetMoviesUseCase
import com.tksko.mymovies.domain.usecase.SearchMoviesUseCase
import com.tksko.mymovies.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val searchResults =
        SingleLiveEvent<List<MovieResult>>()
    val searchResultsLiveData: LiveData<List<MovieResult>> = searchResults

    val moviesList by lazy {
        getMoviesUseCase().cachedIn(viewModelScope)
    }

    fun searchMovies(query: String) = viewModelScope.launch {
        searchResults.postValue(searchMoviesUseCase(query))
    }
}