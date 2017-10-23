package com.niranjandroid.movieshows.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.data.network.NetworkModule
import com.niranjandroid.movieshows.data.prefs.PreferencesHelper
import com.niranjandroid.movieshows.ui.listing.ListingComponent
import com.niranjandroid.movieshows.ui.listing.ListingModule
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric

/**
 * Created by Niranjan P on 4/17/2017.
 */

class App : Application() {

    val mAppComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    private var mListingComponent: ListingComponent?= null

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        init()
    }

    private fun init() {
        initLibraries()
    }

    private fun initLibraries() {
        initStetho()
        initFabric()
        initPreferenceHelper()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initFabric() {
        Fabric.with(this, Crashlytics())
    }

    private fun initPreferenceHelper() {
        PreferencesHelper.init(this, Constants.PREFERENCES_NAME)
    }

    fun getMovieListingComponent() : ListingComponent? {
        mListingComponent = mAppComponent?.plus(ListingModule())
        return mListingComponent
    }

    fun releaseMovieListingComponent() {
        mListingComponent = null
    }
}
