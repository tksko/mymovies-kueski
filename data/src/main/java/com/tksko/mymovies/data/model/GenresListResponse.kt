package com.tksko.mymovies.data.model

import com.google.gson.annotations.SerializedName

data class GenresListResponse(
    @SerializedName("genres")
    val results: List<GenreResponse>
)

fun GenresListResponse.toGenresResultList() = results.map { it.toGenreResult() }