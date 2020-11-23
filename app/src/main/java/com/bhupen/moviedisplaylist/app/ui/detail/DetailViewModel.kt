package com.bhupen.moviedisplaylist.app.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bhupen.moviedisplaylist.common.base.BaseViewModel
import com.bhupen.moviedisplaylist.data.MovieRepository
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) :
    BaseViewModel() {

    private val _movieLiveData = MutableLiveData<MovieEntity>()
    val movieLiveData: LiveData<MovieEntity>
        get() = _movieLiveData

    private var job: Job? = null
    fun getMovieDetail(movieId: Long) {
        job?.cancel()
        job = viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            _movieLiveData.value = movie
        }
    }
}