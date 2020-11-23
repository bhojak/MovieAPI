package com.bhupen.moviedisplaylist.app.di.module

import android.content.Context
import androidx.room.Room
import com.bhupen.moviedisplaylist.common.Constants

import com.bhupen.moviedisplaylist.data.local.dao.MovieDao
import com.bhupen.moviedisplaylist.data.local.dao.RemoteKeysDao
import com.bhupen.moviedisplaylist.data.local.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppPersistenceModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        Constants.DB_NAME
    )
        .build()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(movieDb: MovieDatabase): RemoteKeysDao = movieDb.remoteKeysDao()

    @Singleton
    @Provides
    fun provideMovieDao(movieDb: MovieDatabase): MovieDao = movieDb.movieDao()
}