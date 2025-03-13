package com.tksko.mymovies.data.di

import android.content.Context
import androidx.room.Room
import com.tksko.mymovies.data.database.MyMoviesDatabase
import com.tksko.mymovies.data.database.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesMyMoviesDatabase(@ApplicationContext context: Context): MyMoviesDatabase {
        return Room.databaseBuilder(
            context,
            MyMoviesDatabase::class.java,
            MyMoviesDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesMoviesDao(myMoviesDatabase: MyMoviesDatabase): MoviesDao = myMoviesDatabase.moviesDao()
}