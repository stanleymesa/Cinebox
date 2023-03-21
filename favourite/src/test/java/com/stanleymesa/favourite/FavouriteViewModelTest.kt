package com.stanleymesa.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stanleymesa.core.data.Resource
import com.stanleymesa.favourite.data.usecase.FakeFavouriteUseCase
import com.stanleymesa.favourite.dummy.Dummy
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
class FavouriteViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FavouriteViewModel

    @Before
    fun setUp() {
        viewModel = FavouriteViewModel(FakeFavouriteUseCase())
    }

    @Test
    fun `check favourite movies value`() = runTest {
        val value = viewModel.getAllFavourite().getOrAwaitValue()
        assertEquals(value.getContentIfNotHandled()?.data, arrayListOf(Dummy.favourite()))
    }

    @Test
    fun `check detail movie success`() = runTest {
        val value = viewModel.getAllFavourite().getOrAwaitValue()
        assert(value.getContentIfNotHandled() is Resource.Success)
    }

}