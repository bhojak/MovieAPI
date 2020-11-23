package com.bhupen.moviedisplaylist.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bhupen.moviedisplaylist.common.Constants

import com.bhupen.moviedisplaylist.common.utils.DataResult
import com.bhupen.moviedisplaylist.common.utils.MovieFilter
import com.bhupen.moviedisplaylist.data.local.LocalDataSource
import com.bhupen.moviedisplaylist.data.local.db.MovieDatabase
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.local.entity.RemoteKeysEntity
import com.bhupen.moviedisplaylist.data.local.mapper.toMovieEntity

import com.bhupen.moviedisplaylist.data.remote.RemoteDataSource
import com.bhupen.moviedisplaylist.data.remote.dto.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val sortBy: MovieFilter.SORT,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val database: MovieDatabase
) : RemoteMediator<Int, MovieEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        //return because we are reading from cache and
        if (loadType == LoadType.PREPEND) {
            return MediatorResult.Success(
                endOfPaginationReached = true
            )
        }
        val page = computePageNumber(loadType, state)
        when (val apiResult = remoteDataSource.fetch(sort = sortBy, page = page)) {
            is DataResult.Success -> {
                Log.e("Result", apiResult.toString())
                val moviesFromNetwork = apiResult.data.results
                val endOfPaginationReached = moviesFromNetwork.isEmpty()
                database.withTransaction {
                    //Invalidate local cache if we are resubmitting paging
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.clearAllTables()
                    }
                    insertNewPageData(moviesFromNetwork, endOfPaginationReached, page)

                }
                return MediatorResult.Success(
                    endOfPaginationReached = endOfPaginationReached
                )
            }
            is DataResult.Error -> {
                Log.e("Result", apiResult.e.toString())
                return MediatorResult.Error(
                    apiResult.e
                )
            }
        }

    }

    private suspend fun insertNewPageData(
        moviesFromNetwork: List<MovieDTO>,
        endOfPaginationReached: Boolean,
        page: Int
    ) {
        val nextKey = if (endOfPaginationReached) null else page + 1
        val keys = moviesFromNetwork.map {
            RemoteKeysEntity(
                repoId = it.id,
                nextKey = nextKey
            )
        }
        localDataSource.insertAllMovies(moviesFromNetwork.map { it.toMovieEntity() })
        localDataSource.insertAllKeys(keys)
    }

    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? =
        withContext(Dispatchers.IO) {
            state.lastItemOrNull()?.let {
                localDataSource.findRemoteKeyById(it.id)
            }
        }

    private suspend fun computePageNumber(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): Int =
        when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyFromCurrentPosition(state)
                //Return current page if exists, else return the default page: 1
                remoteKeys?.nextKey?.minus(1) ?: Constants.DEFAULT_PAGE_NUMBER
            }
            LoadType.APPEND -> {
                getRemoteKeyFromLastItem(state)?.nextKey
            }
            else -> null
        }
        //This can never be null, so throw an exception if somebody tried to mess with it.
            ?: throw RuntimeException("Problem within transactions, Page cannot be found.")


    private suspend fun getRemoteKeyFromCurrentPosition(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? =
        withContext(Dispatchers.IO) {
            state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { movieId ->
                    localDataSource.findRemoteKeyById(movieId)
                }
            }
        }

}