package com.stanleymesa.cinebox.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stanleymesa.cinebox.MainCoroutineRule
import com.stanleymesa.cinebox.data.dummy.Dummy
import com.stanleymesa.cinebox.data.usecase.FakeDetailUseCase
import com.stanleymesa.cinebox.getOrAwaitValue
import com.stanleymesa.core.data.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var fakeDetailUseCase: FakeDetailUseCase

    @Before
    fun setUp() {
        fakeDetailUseCase = FakeDetailUseCase()
        viewModel = DetailViewModel(fakeDetailUseCase)
    }

    @Test
    fun `check detail movie value`() = runTest {
        val value = viewModel.getDetailMovie("1").getOrAwaitValue()
        assertEquals(value.getContentIfNotHandled()?.data, Dummy.detail())
    }

    @Test
    fun `check detail movie success`() = runTest {
        val value = viewModel.getDetailMovie("1").getOrAwaitValue()
        assert(value.getContentIfNotHandled() is Resource.Success)
    }

    @Test
    fun `check credit movie value`() = runTest {
        val value = viewModel.getCreditMovie("1").getOrAwaitValue()
        assertEquals(value.getContentIfNotHandled()?.data, Dummy.cast())
    }

    @Test
    fun `check credit movie success`() = runTest {
        val value = viewModel.getCreditMovie("1").getOrAwaitValue()
        assert(value.getContentIfNotHandled() is Resource.Success)
    }

    @Test
    fun `check is favourite = true`() = runTest {
        val value = viewModel.isFavourite("1").getOrAwaitValue()
        assertEquals(true, value.getContentIfNotHandled())
    }

    @Test
    fun `check is favourite inserted`() = runTest {
        viewModel.insertFavourite(Dummy.favourite())
        assertEquals(true, fakeDetailUseCase.isInserted)
    }

    @Test
    fun `check is favourite deleted`() = runTest {
        viewModel.deleteFavouriteById("id")
        assertEquals(true, fakeDetailUseCase.isDeleted)
    }

}