package com.niranjandroid.movieshows.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.data.DatabaseModule
import com.niranjandroid.movieshows.data.network.NetworkModule
import com.niranjandroid.movieshows.data.prefs.PreferencesHelper
import com.niranjandroid.movieshows.ui.details.DetailsComponent
import com.niranjandroid.movieshows.ui.details.DetailsModule
import com.niranjandroid.movieshows.ui.movielisting.ListingComponent
import com.niranjandroid.movieshows.ui.movielisting.ListingModule
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
                .databaseModule(DatabaseModule())
                .build()
    }

    private var mListingComponent: ListingComponent? = null
    private var mDetailsComponent: DetailsComponent? = null

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

    fun getMovieListingComponent(): ListingComponent? {
        mListingComponent = mAppComponent.plus(ListingModule())
        return mListingComponent
    }

    fun releaseMovieListingComponent() {
        mListingComponent = null
    }

    fun getDetailsComponent(): DetailsComponent? {
        mDetailsComponent = mAppComponent.plus(DetailsModule())
        return mDetailsComponent
    }

    fun releaseDetailsComponent() {
        mDetailsComponent = null
    }
}
