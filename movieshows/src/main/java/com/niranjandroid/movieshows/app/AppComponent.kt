package com.niranjandroid.movieshows.app

import com.niranjandroid.movieshows.data.network.NetworkModule
import com.niranjandroid.movieshows.ui.details.DetailsComponent
import com.niranjandroid.movieshows.ui.details.DetailsModule
import com.niranjandroid.movieshows.ui.listing.ListingComponent
import com.niranjandroid.movieshows.ui.listing.ListingModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Niranjan P on 8/27/2017.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class))
interface AppComponent {
    fun plus(mListingModule: ListingModule) : ListingComponent
    fun plus(mDetailsModule: DetailsModule) : DetailsComponent
}
