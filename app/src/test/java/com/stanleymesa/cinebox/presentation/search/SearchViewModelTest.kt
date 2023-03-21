package com.stanleymesa.cinebox.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.stanleymesa.cinebox.MainCoroutineRule
import com.stanleymesa.cinebox.data.usecase.FakeSearchUseCase
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
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(FakeSearchUseCase())
    }

    @Test
    fun `check searched movie value`() = runTest {
        val data: PagingData<Movie> = PagingData.empty()

        val value = viewModel.getSearchMovie("Iron Man").getOrAwaitValue()

        assertEquals(data, value.getContentIfNotHandled())
    }

}