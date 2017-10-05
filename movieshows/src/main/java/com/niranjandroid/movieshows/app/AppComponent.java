package com.niranjandroid.movieshows.app;

import com.niranjandroid.movieshows.data.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Niranjan P on 8/27/2017.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {
}
