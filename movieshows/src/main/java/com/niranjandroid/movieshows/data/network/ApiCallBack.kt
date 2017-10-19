package com.niranjandroid.movieshows.data.network

interface ApiCallBack<T> {
    fun onSuccess(t: T)

    fun onError(throwable: Throwable)
}