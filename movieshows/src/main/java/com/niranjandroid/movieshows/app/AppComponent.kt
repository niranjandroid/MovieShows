package com.niranjandroid.movieshows.app

import com.niranjandroid.movieshows.data.network.NetworkModule
import com.niranjandroid.movieshows.ui.movielisting.MovieListingComponent
import com.niranjandroid.movieshows.ui.movielisting.MovieListingModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Niranjan P on 8/27/2017.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class))
interface AppComponent {
    fun plus(mMovieListingModule: MovieListingModule) : MovieListingComponent
}
