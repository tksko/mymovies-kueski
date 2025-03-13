package com.tksko.mymovies.domain.usecase

import com.tksko.mymovies.domain.model.MovieResult

interface SearchMoviesUseCase{
    suspend operator fun invoke(query: String): List<MovieResult>
}