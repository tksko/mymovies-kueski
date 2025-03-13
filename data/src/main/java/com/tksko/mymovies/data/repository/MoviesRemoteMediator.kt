package com.tksko.mymovies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.tksko.mymovies.data.api.MoviesApiServices
import com.tksko.mymovies.data.database.MoviesDao
import com.tksko.mymovies.data.model.MovieDbModel
import com.tksko.mymovies.data.model.toMovieResult
import com.tksko.mymovies.domain.model.MovieResult
import javax.inject.Inject
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val moviesApiServices: MoviesApiServices,
    private val moviesDao: MoviesDao
) : RemoteMediator<Int, MovieDbModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieDbModel>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    1
                } else {
                    lastItem.page + 1
                }
            }
        }

        return try {
            val items = getMovies(page)
            if (loadType == LoadType.REFRESH) {
                moviesDao.clearMovies()
            }
            moviesDao.insertMovies(items.map { movie -> MovieDbModel(movie, page) })
            MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    @Throws(Exception::class)
    private suspend fun getMovies(page: Int): List<MovieResult> =
        moviesApiServices.getMovies(page).body()?.results?.map {
            it.toMovieResult()
        } ?: emptyList()
}