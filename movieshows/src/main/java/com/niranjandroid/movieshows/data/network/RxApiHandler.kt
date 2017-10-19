package com.niranjandroid.movieshows.data.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxApiHandler<T> {

    fun handle(observable: Observable<T>, apiCallBack: ApiCallBack<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success -> apiCallBack.onSuccess(success) },
                        { throwable -> apiCallBack.onError(throwable) })
    }
}