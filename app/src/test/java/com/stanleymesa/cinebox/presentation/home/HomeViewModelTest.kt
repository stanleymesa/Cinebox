package com.stanleymesa.cinebox.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.stanleymesa.cinebox.MainCoroutineRule
import com.stanleymesa.cinebox.data.usecase.FakeHomeUseCase
import com.stanleymesa.cinebox.getOrAwaitValue
import com.stanleymesa.core.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(FakeHomeUseCase())
    }

    @Test
    fun `check now playing movie value`() = runTest {
        val data: PagingData<Movie> = PagingData.empty()

        viewModel.getAllNowPlayingMovie()
        val value = viewModel.nowPlayingMovie.getOrAwaitValue()

        assertEquals(data, value.getContentIfNotHandled())
    }

    @Test
    fun `check upcoming movie value`() = runTest {
        val data: PagingData<Movie> = PagingData.empty()

        viewModel.getAllUpcomingMovie()
        val value = viewModel.upcomingMovie.getOrAwaitValue()

        assertEquals(data, value.getContentIfNotHandled())
    }

    @Test
    fun `check top rated movie value`() = runTest {
        val data: PagingData<Movie> = PagingData.empty()

        viewModel.getAllTopRatedMovie()
        val value = viewModel.topRatedMovie.getOrAwaitValue()

        assertEquals(data, value.getContentIfNotHandled())
    }

}