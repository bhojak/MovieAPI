package com.bhupen.moviedisplaylist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import com.bhupen.moviedisplaylist.common.utils.MovieFilter
import com.bhupen.moviedisplaylist.data.local.LocalDataSource
import com.bhupen.moviedisplaylist.data.local.db.MovieDatabase
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
 No need to put this behind abstraction, We don't intend to create a fake repo and
 we can easily mock this in tests.
 */
class MovieRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
    /*
    We have to pass database in due to multi-transaction issues.
    https://medium.com/androiddevelopers/threading-models-in-coroutines-and-android-sqlite-api-6cab11f7eb90
     */
    private val database: MovieDatabase,
        private val pageConfig: PagingConfig
) {

    //Builds corresponding pagination sources
    fun fetchMovies(
        sortBy: MovieFilter.SORT
    ): Flow<PagingData<MovieEntity>> {
        val localPagingSourceFactory = { localDataSource.getAllMovies() }
        val remoteMediator =
            MovieRemoteMediator(sortBy, remoteDataSource, localDataSource, database)
        return Pager(
            pageConfig,
            pagingSourceFactory = localPagingSourceFactory,
            remoteMediator = remoteMediator
        ).flow
    }

    suspend fun getMovieById(id: Long): MovieEntity = withContext(Dispatchers.IO) {
        localDataSource.findMovieById(id)
            ?: throw RuntimeException("Movie with Id does not exist in page!")
    }
}

