package com.niranjandroid.movieshows.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.niranjandroid.movieshows.Constants;
import com.niranjandroid.movieshows.data.network.NetworkModule;
import com.niranjandroid.movieshows.data.prefs.PreferencesHelper;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Niranjan P on 4/17/2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // should not init app in this process.
            return;
        }
        LeakCanary.install(this);
        init();
    }

    private void init() {
        initComponent();
        initLibraries();
    }

    private void initLibraries() {
        initStetho();
        initFabric();
        initPreferenceHelper();
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    private void initPreferenceHelper() {
        PreferencesHelper.init(this, Constants.PREFERENCES_NAME);
    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .networkModule(new NetworkModule())
            .build();
    }

}
