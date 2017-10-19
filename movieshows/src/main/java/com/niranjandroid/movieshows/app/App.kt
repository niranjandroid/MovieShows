package com.niranjandroid.movieshows.app

import android.app.Application

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.data.network.NetworkModule
import com.niranjandroid.movieshows.data.prefs.PreferencesHelper
import com.squareup.leakcanary.LeakCanary

import io.fabric.sdk.android.Fabric

/**
 * Created by Niranjan P on 4/17/2017.
 */

class App : Application() {

    private var mAppComponent: AppComponent ?= null

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        init()
    }

    private fun init() {
        initComponent()
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

    private fun initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

}
