package com.stanleymesa.core.data.source.remote

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import com.stanleymesa.core.MainCoroutineRule
import com.stanleymesa.core.data.source.local.room.MovieDatabase
import com.stanleymesa.core.data.source.remote.network.FakeApiService
import com.stanleymesa.core.getOrAwaitValue
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun setUp() {
        movieDatabase = Room.inMemoryDatabaseBuilder(
            Mockito.mock(Context::class.java),
            MovieDatabase::class.java
        ).build()

        remoteDataSource = RemoteDataSource(
            apiService = FakeApiService(),
            database = movieDatabase,
            movieDao = movieDatabase.movieDao()
        )
    }

    @Test
    fun `check now playing movie value not null`() = runTest {
        val value = remoteDataSource.getAllNowPlayingMovie().asLiveData()
        assertNotNull(value)
    }

    @Test
    fun `check upcoming movie value not null`() = runTest {
        val value = remoteDataSource.getAllUpcomingMovie().asLiveData()
        assertNotNull(value)
    }

    @Test
    fun `check top rated movie value not null`() = runTest {
        val value = remoteDataSource.getAllTopRatedMovie().asLiveData()
        assertNotNull(value)
    }

    @Test
    fun `check detail movie resource not null`() = runTest {
        val value = remoteDataSource.getDetailMovie("1").asLiveData()
        assertNotNull(value.getOrAwaitValue())
    }

    @Test
    fun `check credit movie value not null`() = runTest {
        val value = remoteDataSource.getCreditMovie("1").asLiveData()
        assertNotNull(value.getOrAwaitValue())
    }

    @Test
    fun `check searched movie value not null`() = runTest {
        val value = remoteDataSource.getSearchMovie("Iron Man").asLiveData()
        assertNotNull(value.getOrAwaitValue())
    }

}