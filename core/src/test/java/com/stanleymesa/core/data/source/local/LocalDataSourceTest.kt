package com.stanleymesa.core.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.stanleymesa.cinebox.data.dummy.Dummy
import com.stanleymesa.core.MainCoroutineRule
import com.stanleymesa.core.data.Resource
import com.stanleymesa.core.data.source.local.room.FakeMovieDao
import com.stanleymesa.core.getOrAwaitValue
import com.stanleymesa.core.utils.DataMapper
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var localDataSource: LocalDataSource
    private lateinit var fakeMovieDao: FakeMovieDao

    @Before
    fun setUp() {
        fakeMovieDao = FakeMovieDao()
        localDataSource = LocalDataSource(fakeMovieDao)
    }

    @Test
    fun `check is favourite = true`() = runTest {
        val value = localDataSource.isFavourite("1").asLiveData().getOrAwaitValue()
        assertTrue(value)
    }

    @Test
    fun `check is favourite inserted`() = runTest {
        localDataSource.insertFavourite(Dummy.favourite())
        assertTrue(fakeMovieDao.inserted)
    }

    @Test
    fun `check is favourite deleted`() = runTest {
        localDataSource.deleteFavouriteById("1")
        assertTrue(fakeMovieDao.deleted)
    }

    @Test
    fun `check favourite value`() = runTest {
        val value = localDataSource.getAllFavourite().asLiveData().getOrAwaitValue()
        assertEquals(DataMapper.mapFavouriteEntitiesToDomains(arrayListOf(Dummy.favouriteEntity())),
            value.data)
    }

    @Test
    fun `check get favourite is success`() = runTest {
        val value = localDataSource.getAllFavourite().asLiveData().getOrAwaitValue()
        assert(value is Resource.Success)
    }

}