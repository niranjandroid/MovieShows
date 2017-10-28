package com.niranjandroid.movieshows.ui.listing

import android.util.Log
import com.niranjandroid.movieshows.data.database.GenreDao
import com.niranjandroid.movieshows.data.database.MovieListDao
import com.niranjandroid.movieshows.data.model.Genre
import com.niranjandroid.movieshows.data.model.Genres
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
                            var movieListDao: MovieListDao,
                            var genreDao : GenreDao) : ListingContract.Interactor {
    override fun fetchPopularMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getPopularMovies(pageNum), apiCallBack)

    override fun fetchGenres(apiCallBack: ApiCallBack<Genres>): Disposable =
            RxApiHandler<Genres>().handle(apiService.getGenres(), apiCallBack)

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

    override fun saveGenres(genres: List<Genre>) {
        Completable.fromAction {genreDao.insert(genres)}.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onComplete() {
                        Log.d("SAVING_Genres", "saved successfully")
                    }

                    override fun onError(@NonNull e: Throwable) {
                        Log.d("ERROR_SAVING_Genres", e.message)
                    }
                })
    }

    override fun getMovieModelFromLocalByPage(pageNum: String, apiCallBack: ApiCallBack<MovieListModel>): Disposable {
        return RxApiHandler<MovieListModel>().handle(movieListDao.getMovieListModelByPage(pageNum), apiCallBack)
    }
}
