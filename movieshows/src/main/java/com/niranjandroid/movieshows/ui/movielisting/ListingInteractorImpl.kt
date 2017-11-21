package com.niranjandroid.movieshows.ui.movielisting

import android.util.Log
import com.niranjandroid.movieshows.data.database.MovieListDao
import com.niranjandroid.movieshows.data.model.MovieListModel
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
 * Created by Niranjan P on 8/27/2017.
 */

class ListingInteractorImpl(var apiService: ApiService,
                            var movieListDao: MovieListDao) : ListingContract.Interactor {
    override fun fetchPopularMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getPopularMovies(pageNum), apiCallBack)

    override fun fetchNowPlayingMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getNowPlayingMovies(pageNum), apiCallBack)

    override fun fetchUpcomingMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getUpcomingMovies(pageNum), apiCallBack)

    override fun fetchTopRatedMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getTopRatedMovies(pageNum), apiCallBack)

    override fun saveMovieList(movieListModel: MovieListModel) {
        Completable.fromAction {movieListDao.insert(movieListModel)}.subscribeOn(Schedulers.io())
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



    override fun getMovieModelFromLocalByPage(pageNum: String, apiCallBack: ApiCallBack<MovieListModel>): Disposable {
        return RxApiHandler<MovieListModel>().handle(movieListDao.getMovieListModelByPage(pageNum), apiCallBack)
    }
}
