package com.tksko.mymovies.data.usecase

import com.tksko.mymovies.domain.repository.MoviesRepository
import com.tksko.mymovies.domain.usecase.SearchMoviesUseCase
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : SearchMoviesUseCase {
    override suspend operator fun invoke(query: String) = moviesRepository.searchMovies(query)
}