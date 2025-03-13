package com.tksko.mymovies.data.api

import com.tksko.mymovies.data.model.GenresListResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenresApiServices {
    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresListResponse>
}