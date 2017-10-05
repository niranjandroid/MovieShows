package com.niranjandroid.movieshows.data.network;

public interface ApiCallBack<T> {
    void onSuccess(T t);

    void onError(Throwable throwable);
}