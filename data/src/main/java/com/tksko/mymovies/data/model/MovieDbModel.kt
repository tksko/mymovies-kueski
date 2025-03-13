package com.tksko.mymovies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tksko.mymovies.domain.model.MovieResult
import java.util.Date

@Entity
data class MovieDbModel(
    @PrimaryKey
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val releaseDate: Date? = null,
    val page: Int = 1
) {
    constructor(movieResult: MovieResult, page: Int) : this(
        movieResult.id,
        movieResult.name,
        movieResult.overview,
        movieResult.backdropPath,
        movieResult.posterPath,
        movieResult.releaseDate,
        page
    )
}

fun MovieDbModel.toMovieResult() = MovieResult(
    id = id,
    name = title,
    overview = overview,
    backdropPath = backdropPath,
    posterPath = posterPath,
    releaseDate = releaseDate
)