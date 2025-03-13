package com.tksko.mymovies.data.repository

import com.tksko.mymovies.data.api.GenresApiServices
import com.tksko.mymovies.data.model.toGenresResultList
import com.tksko.mymovies.domain.model.GenreResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    private val genresApiServices: GenresApiServices
) {
    private var cachedGenres = emptyList<GenreResult>()

    suspend fun getGenreName(genreId: Int?): String? {
        if (cachedGenres.isEmpty()) {
            cachedGenres = getGenres()
        }
        return cachedGenres.firstOrNull { it.id == genreId }?.name
    }

    private suspend fun getGenres() = try {
        genresApiServices.getGenres().body()?.toGenresResultList() ?: emptyList()
    } catch (error: Exception) {
        emptyList()
    }
}