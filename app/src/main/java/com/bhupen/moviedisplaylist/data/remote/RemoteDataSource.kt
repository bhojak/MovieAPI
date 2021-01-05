package com.bhupen.moviedisplaylist.data.remote

import com.bhupen.moviedisplaylist.common.utils.DataResult
import com.bhupen.moviedisplaylist.common.utils.MovieFilter
import com.bhupen.moviedisplaylist.data.remote.dto.MovieDetailDTO
import com.bhupen.moviedisplaylist.data.remote.dto.MovieResponseDTO


interface RemoteDataSource {
    suspend fun fetch(sort: MovieFilter.SORT, page: Int): DataResult<MovieResponseDTO>
    suspend fun fetchMovieDetails(movie_id: Int): DataResult<MovieDetailDTO>
}

