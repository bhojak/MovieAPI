package com.bhupen.moviedisplaylist.data.local.mapper

import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.remote.dto.MovieDTO


fun MovieDTO.toMovieEntity(): MovieEntity {
    return MovieEntity(
        popularity = popularity,
        voteCount = voteCount,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate,
        posterLink = posterLink
    )
}
