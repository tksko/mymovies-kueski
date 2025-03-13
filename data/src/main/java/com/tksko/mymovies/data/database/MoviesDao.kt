package com.tksko.mymovies.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tksko.mymovies.data.model.MovieDbModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDbModel>)

    @Query("SELECT * FROM MovieDbModel WHERE title LIKE :query")
    suspend fun searchMovies(query: String): List<MovieDbModel>

    @Query("SELECT * FROM MovieDbModel order by page asc")
    fun getMoviesPage(): PagingSource<Int, MovieDbModel>

    @Query("DELETE FROM MovieDbModel")
    suspend fun clearMovies()
}