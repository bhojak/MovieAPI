package com.bhupen.moviedisplaylist.app.di.entry



import com.bhupen.moviedisplaylist.app.di.factory.MovieDbFragmentFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface MovieDbFragmentFactoryEntryPoint {
    fun getFragmentFactory(): MovieDbFragmentFactory
}