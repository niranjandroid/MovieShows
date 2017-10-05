package com.niranjandroid.movieshows.data.network;

import android.app.Application;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.niranjandroid.movieshows.BuildConfig;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Niranjan P on 08/18/2017.
 */

@Module
public class NetworkModule {

    private String mBaseUrl;
    private static final int SIZE_10MB = 1024 * 1024 * 10;

    public NetworkModule() {
        mBaseUrl = BuildConfig.BASE_URL;
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .build();
        return retrofit;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor, Interceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

        return client;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = SIZE_10MB; // 10 MB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return (Interceptor.Chain chain) -> getResponse(chain);
    }

    private Response getResponse(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
            .method(original.method(), original.body())
            .build();

        Response response = chain.proceed(request);
        return response;
    }

}
