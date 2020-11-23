package com.bhupen.moviedisplaylist.app.di.module

import androidx.fragment.app.Fragment
import com.bhupen.moviedisplaylist.app.di.key.FragmentKey
import com.bhupen.moviedisplaylist.app.ui.detail.DetailFragment
import com.bhupen.moviedisplaylist.app.ui.discover.MovieListFragment
import com.bhupen.moviedisplaylist.data.local.LocalDataSource
import com.bhupen.moviedisplaylist.data.local.LocalDataSourceImpl
import com.bhupen.moviedisplaylist.data.remote.RemoteDataSource
import com.bhupen.moviedisplaylist.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class MovieModule {
    /**
     * Binds this fragment into a map of fragments to later be used by [FragmentFactory]
     */
    @Binds
    @IntoMap
    @FragmentKey(MovieListFragment::class)
    abstract fun bindMovieFragment(movieListFragment: MovieListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(detailFragment: DetailFragment): Fragment

    @Binds
    @ActivityScoped
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    @ActivityScoped
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}