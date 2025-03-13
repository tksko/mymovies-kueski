package com.tksko.mymovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tksko.mymovies.data.model.MovieDbModel

@Database(entities = [MovieDbModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyMoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        const val DB_NAME = "myMoviesAppDB.db"
    }

}