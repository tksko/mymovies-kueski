package com.tksko.mymovies.data.model

import com.google.gson.annotations.SerializedName
import com.tksko.mymovies.domain.model.GenreResult

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun GenreResponse.toGenreResult() = GenreResult(
    id = id,
    name = name
)