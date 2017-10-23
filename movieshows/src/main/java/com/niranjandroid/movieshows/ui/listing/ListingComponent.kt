package com.niranjandroid.movieshows.ui.listing


import com.niranjandroid.movieshows.ui.base.BaseScope

import dagger.Subcomponent

/**
 * Created by Niranjan P on 8/22/2017.
 */

@BaseScope
@Subcomponent(modules = arrayOf(ListingModule::class))
interface ListingComponent {
    fun inject(fragment: ListingFragment)
}
