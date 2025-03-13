package com.tksko.mymovies.data.di

import com.tksko.mymovies.data.repository.MoviesRepositoryImpl
import com.tksko.mymovies.data.usecase.GetMoviesUseCaseImpl
import com.tksko.mymovies.data.usecase.SearchMoviesUseCaseImpl
import com.tksko.mymovies.domain.repository.MoviesRepository
import com.tksko.mymovies.domain.usecase.GetMoviesUseCase
import com.tksko.mymovies.domain.usecase.SearchMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    fun bindsGetMoviesUseCase(impl: GetMoviesUseCaseImpl): GetMoviesUseCase

    @Binds
    fun bindsSearchMoviesUseCase(impl: SearchMoviesUseCaseImpl): SearchMoviesUseCase
}