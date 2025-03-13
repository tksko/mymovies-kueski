package com.tksko.mymovies.domain.model

import android.os.Parcelable
import com.tksko.mymovies.domain.config.POSTER_BASE_URL
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class MovieResult(
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val releaseDate: Date? = null,
    val genreName: String? = null
) : Parcelable {

    fun getPoster() = POSTER_BASE_URL + posterPath.orEmpty()

    fun getBackdrop() = POSTER_BASE_URL + (backdropPath ?: posterPath.orEmpty())
}