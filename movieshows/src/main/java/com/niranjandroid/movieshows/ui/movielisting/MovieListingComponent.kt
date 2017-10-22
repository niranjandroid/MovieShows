package com.niranjandroid.movieshows.ui.movielisting


import com.niranjandroid.movieshows.ui.base.BaseScope

import dagger.Subcomponent

/**
 * Created by Niranjan P on 8/22/2017.
 */

@BaseScope
@Subcomponent(modules = arrayOf(MovieListingModule::class))
interface MovieListingComponent {
    fun inject(fragment: MovieListingFragment)
}
