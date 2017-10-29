package com.niranjandroid.movieshows.ui.details

import android.util.Log
import com.niranjandroid.movieshows.data.database.MovieDao
import com.niranjandroid.movieshows.data.model.ImagesModel
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.data.network.RxApiHandler
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Niranjan P on 10/24/2017.
 */
class DetailsInteractorImpl(var apiService: ApiService, var movieDao: MovieDao) : DetailsContract.Interactor {

    override fun getImages(id: Long?, apiCallBack: ApiCallBack<ImagesModel>): Disposable? {
        return RxApiHandler<ImagesModel>().handle(apiService.getImages(id, "en,null"), apiCallBack)
    }

    override fun fetchMovieDetails(id: Long?, apiCallBack: ApiCallBack<MovieModel>): Disposable? {
        return RxApiHandler<MovieModel>().handle(apiService.getMovieDetails(id,"en,null", "videos,images"), apiCallBack)
    }

    override fun saveMovieDetails(movieModel: MovieModel) {
        Completable.fromAction {
            movieDao.insert(movieModel)}.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(@NonNull d: Disposable) {
                    }
                    override fun onComplete() {
                        Log.d("SAVING_MM", "saved successfully")
                    }
                    override fun onError(@NonNull e: Throwable) {
                        Log.d("ERROR_SAVING_MM", e.message)
                    }
                })
    }
}