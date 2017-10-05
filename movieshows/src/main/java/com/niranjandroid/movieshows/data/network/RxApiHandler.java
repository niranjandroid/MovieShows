package com.niranjandroid.movieshows.data.network;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxApiHandler<T> {

    public void handle(Observable<T> observable, ApiCallBack<T> apiCallBack) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success -> apiCallBack.onSuccess(success),
                throwable -> apiCallBack.onError(throwable)
            );
    }
}