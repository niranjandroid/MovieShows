package com.niranjandroid.movieshows.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Niranjan P on 4/18/2017.
 */

@Module
class AppModule(internal var application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }
}
