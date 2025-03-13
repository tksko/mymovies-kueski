package com.tksko.mymovies.data.usecase

import com.tksko.mymovies.domain.repository.MoviesRepository
import com.tksko.mymovies.domain.usecase.GetMoviesUseCase
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMoviesUseCase {
    override operator fun invoke() = moviesRepository.getMovies()
}