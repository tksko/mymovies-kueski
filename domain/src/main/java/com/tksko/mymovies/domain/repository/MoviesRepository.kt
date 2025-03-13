package com.tksko.mymovies.domain.repository

import androidx.paging.PagingData
import com.tksko.mymovies.domain.model.MovieResult
import kotlinx.coroutines.flow.Flow

interface
MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieResult>>
}