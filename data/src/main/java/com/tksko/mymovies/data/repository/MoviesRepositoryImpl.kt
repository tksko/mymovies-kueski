package com.tksko.mymovies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.tksko.mymovies.data.config.PAGE_SIZE
import com.tksko.mymovies.data.database.MoviesDao
import com.tksko.mymovies.data.model.toMovieResult
import com.tksko.mymovies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val moviesRemoteMediator: MoviesRemoteMediator
) : MoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE
        ),
        remoteMediator = moviesRemoteMediator,
        pagingSourceFactory = { moviesDao.getMoviesPage() }
    ).flow.map { pagingData ->
        pagingData.map { entity -> entity.toMovieResult() }
    }
}