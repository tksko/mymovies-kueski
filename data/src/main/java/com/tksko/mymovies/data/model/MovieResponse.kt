package com.tksko.mymovies.data.model

import com.google.gson.annotations.SerializedName
import com.tksko.mymovies.domain.model.MovieResult
import java.util.Date

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date
)

fun MovieResponse.toMovieResult(genreName: String?) = MovieResult(
    id = id,
    name = title,
    overview = overview,
    backdropPath = backdropPath,
    posterPath = posterPath,
    releaseDate = releaseDate,
    genreName = genreName
)