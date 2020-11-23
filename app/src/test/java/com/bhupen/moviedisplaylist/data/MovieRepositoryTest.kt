package com.bhupen.moviedisplaylist.data

import androidx.paging.PagingConfig
import com.bhupen.moviedisplaylist.common.utils.CoroutineTestRule
import com.bhupen.moviedisplaylist.data.local.LocalDataSource
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.remote.RemoteDataSource

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {


    val remote = mock<RemoteDataSource>()
    val local = mock<LocalDataSource>()

    val repo = MovieRepository(remote, local, mock(), PagingConfig(1))

    @get:Rule
    val rule = CoroutineTestRule()

    @Test(expected = RuntimeException::class)
    fun `should throw when id doesnt exist`() = runBlockingTest {
        repo.getMovieById(6969)
    }


    @Test
    fun `should return movie if id exists`() = runBlocking {
        val movieEntity = MovieEntity(
                1,
                1.1,
                1,
                "",
                "",
                "",
                "",
                1.1,
                "",
                ""
        )
        whenever(local.findMovieById(any())).thenReturn(
                movieEntity
        )

        assertEquals(repo.getMovieById(1), movieEntity)
    }
}