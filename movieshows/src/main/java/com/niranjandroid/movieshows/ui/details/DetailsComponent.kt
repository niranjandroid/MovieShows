package com.niranjandroid.movieshows.ui.details

import com.niranjandroid.movieshows.ui.base.BaseScope
import dagger.Subcomponent

/**
 * Created by Niranjan P on 10/24/2017.
 */
@BaseScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(fragment: DetailsFragment)
}