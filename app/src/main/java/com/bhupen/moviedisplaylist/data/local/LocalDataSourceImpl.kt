package com.bhupen.moviedisplaylist.data.local

import androidx.paging.PagingSource
import com.bhupen.moviedisplaylist.data.local.dao.MovieDao
import com.bhupen.moviedisplaylist.data.local.dao.RemoteKeysDao
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.local.entity.RemoteKeysEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteKeysDao: RemoteKeysDao
) : LocalDataSource {
    override suspend fun insertAllKeys(list: List<RemoteKeysEntity>) {
        remoteKeysDao.insertAll(list)
    }

    override fun getAllMovies(): PagingSource<Int, MovieEntity> {
        return movieDao.getAllMovies()
    }

    override suspend fun insertAllMovies(list: List<MovieEntity>) {
        movieDao.insertAll(list)
    }

    override suspend fun findRemoteKeyById(id: Long): RemoteKeysEntity? {
        return remoteKeysDao.getRemoteKeyById(id)
    }

    override suspend fun findMovieById(id: Long): MovieEntity? {
        return movieDao.getMovieById(id)
    }

    override suspend fun clearAllTables() {
        movieDao.clearMovies()
        remoteKeysDao.clearRemoteKeys()
    }
}