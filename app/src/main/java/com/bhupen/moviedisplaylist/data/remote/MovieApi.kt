package com.bhupen.moviedisplaylist.data.remote


import com.bhupen.moviedisplaylist.data.remote.dto.MovieResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying (
             @Query("page") page: Int,
             @Query("language") lang: String = "en"
    ): Response<MovieResponseDTO>

// TODO implement this two calls.
    // currently we take details from MovieDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_Id: Int,
        @Query("language") language: String = "en"
    ): Response<MovieResponseDTO>

    @GET("collection/{collection_id}")
    suspend fun getCollection(
        @Path("collection_id") collection_id: Int,
        @Query("language") language: String = "en"
    ): Response<MovieResponseDTO>
}

