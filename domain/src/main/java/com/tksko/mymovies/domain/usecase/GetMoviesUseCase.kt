package com.tksko.mymovies.domain.usecase

import androidx.paging.PagingData
import com.tksko.mymovies.domain.model.MovieResult
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    operator fun invoke(): Flow<PagingData<MovieResult>>
}