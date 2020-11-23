package com.bhupen.moviedisplaylist.app.di.module

import androidx.paging.PagingConfig
import com.bhupen.moviedisplaylist.common.Constants
import com.bhupen.moviedisplaylist.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object MovieNonAbstractModule {

    @Provides
    @ActivityScoped
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(
        MovieApi::class.java
    )

    @Provides
    fun providePageConfig(): PagingConfig =
        PagingConfig(Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false)

}