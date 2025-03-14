package com.tksko.mymovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tksko.mymovies.data.usecase.GetMoviesUseCaseImpl
import com.tksko.mymovies.data.usecase.SearchMoviesUseCaseImpl
import com.tksko.mymovies.domain.model.MovieResult
import com.tksko.mymovies.domain.repository.MoviesRepository
import com.tksko.mymovies.domain.usecase.GetMoviesUseCase
import com.tksko.mymovies.domain.usecase.SearchMoviesUseCase
import com.tksko.mymovies.ui.movies_home.viewmodel.MoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    @RelaxedMockK
    private lateinit var searchResultsObserver: Observer<List<MovieResult>>

    @MockK
    private lateinit var testResult: MovieResult

    private lateinit var moviesViewModel: MoviesViewModel

    private lateinit var searchMoviesUseCase: SearchMoviesUseCase

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        searchMoviesUseCase = SearchMoviesUseCaseImpl(moviesRepository)
        getMoviesUseCase = GetMoviesUseCaseImpl(moviesRepository)
        moviesViewModel = MoviesViewModel(
            getMoviesUseCase,
            searchMoviesUseCase
        ).apply {
            searchResultsLiveData.observeForever(searchResultsObserver)
        }
    }

    private fun setupSearchResults(results: List<MovieResult>) {
        coEvery {
            moviesRepository.searchMovies(any())
        } returns results
    }

    @Test
    fun `Should return empty movies list when searched query has no results`() {
        runTest {
            val resultList = emptyList<MovieResult>()
            setupSearchResults(resultList)

            moviesViewModel.searchMovies(TEST_SEARCH_QUERY)

            verify { searchResultsObserver.onChanged(eq(resultList)) }
        }
    }

    @Test
    fun `Should return movies list result when searched query has at least one result`() {
        runTest {
            val resultList = listOf(testResult)
            setupSearchResults(resultList)

            moviesViewModel.searchMovies(TEST_SEARCH_QUERY)

            verify { searchResultsObserver.onChanged(eq(resultList)) }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        moviesViewModel.searchResultsLiveData.removeObserver(searchResultsObserver)
    }

    private companion object {
        const val TEST_SEARCH_QUERY = "test"
    }
}