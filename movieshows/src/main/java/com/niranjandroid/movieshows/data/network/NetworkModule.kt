package com.niranjandroid.movieshows.data.network

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.niranjandroid.movieshows.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton




/**
 * Created by Niranjan P on 08/18/2017.
 */

@Module
class NetworkModule {

    private val mBaseUrl: String

    init {
        mBaseUrl = BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        return client
    }

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = SIZE_10MB // 10 MB
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    internal fun provideInterceptor(): Interceptor {
        return Interceptor { chain -> getResponse(chain) }
    }

    @Throws(IOException::class)
    private fun getResponse(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language", "en-US")
                .build()
        val request = original.newBuilder()
                .method(original.method(), original.body())
                .url(url)
                .build()

        val response = chain.proceed(request)
        return response
    }

    companion object {
        private val SIZE_10MB = 1024 * 1024 * 10
    }

}
