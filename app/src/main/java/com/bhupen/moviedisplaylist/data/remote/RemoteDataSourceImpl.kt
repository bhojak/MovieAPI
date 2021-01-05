package com.bhupen.moviedisplaylist.data.remote

import com.bhupen.moviedisplaylist.common.base.BaseDataSource
import com.bhupen.moviedisplaylist.common.utils.DataResult
import com.bhupen.moviedisplaylist.common.utils.DateTimeUtil
import com.bhupen.moviedisplaylist.common.utils.MovieFilter
import com.bhupen.moviedisplaylist.data.remote.dto.MovieDetailDTO
import com.bhupen.moviedisplaylist.data.remote.dto.MovieResponseDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val movieApi: MovieApi) : BaseDataSource(),
    RemoteDataSource {
    override suspend fun fetch(sort: MovieFilter.SORT, page: Int): DataResult<MovieResponseDTO> {
        return invokeApi {
            movieApi.getNowPlaying(page = page)
        }
    }

    override suspend fun fetchMovieDetails(movie_id: Int): DataResult<MovieDetailDTO> {
        return invokeApi {
            movieApi.getMovieDetail(movie_id)
        }
    }
}