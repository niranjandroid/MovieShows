package com.niranjandroid.movieshows.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.data.prefs.PreferencesHelper
import com.niranjandroid.movieshows.ui.movielisting.MovieListingComponent
import com.niranjandroid.movieshows.ui.movielisting.MovieListingModule
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
                .build()
    }

    //private var mAppComponent : AppComponent ?= null

    private var mMovieListingComponent : MovieListingComponent ?= null

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

    fun getMovieListingComponent() : MovieListingComponent? {
        mMovieListingComponent = mAppComponent?.plus(MovieListingModule())
        return mMovieListingComponent
    }

    fun releaseMovieListingComponent() {
        mMovieListingComponent = null
    }
}
