package com.bhupen.moviedisplaylist.app.ui.discover

import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity


interface MovieView {
    fun initRecyclerView()
    fun renderMovieList(list: PagingData<MovieEntity>)
    fun renderMovieLoadState(state: CombinedLoadStates)
    fun onMovieItemClicked(movieId: Long)
}