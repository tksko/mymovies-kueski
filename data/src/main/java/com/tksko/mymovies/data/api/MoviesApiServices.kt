package com.tksko.mymovies.data.api

import com.tksko.mymovies.data.model.MoviesListResponse
import com.tksko.mymovies.domain.config.TMDB_PARAM_PAGE
import com.tksko.mymovies.domain.config.TMDB_PARAM_INCLUDE_ADULT
import com.tksko.mymovies.domain.config.TMDB_PARAM_INCLUDE_VIDEO
import com.tksko.mymovies.domain.config.TMDB_PARAM_SORT_BY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiServices {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query(TMDB_PARAM_PAGE) page: Int,
        @Query(TMDB_PARAM_INCLUDE_ADULT) includeAdult: Boolean = false,
        @Query(TMDB_PARAM_INCLUDE_VIDEO) includeVideo: Boolean = false,
        @Query(TMDB_PARAM_SORT_BY) sortBy: String = "popularity.desc"
    ): Response<MoviesListResponse>
}