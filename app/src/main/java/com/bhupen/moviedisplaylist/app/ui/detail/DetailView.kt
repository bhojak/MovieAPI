package com.bhupen.moviedisplaylist.app.ui.detail

import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity


interface DetailView {
    fun renderMovieDetail(movieEntity: MovieEntity)
}