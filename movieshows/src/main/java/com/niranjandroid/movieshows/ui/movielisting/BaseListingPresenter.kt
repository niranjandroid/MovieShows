package com.niranjandroid.movieshows.ui.movielisting

import android.util.Log
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Niranjan P on 11/9/2017.
 */
abstract class BaseListingPresenter(private var interactor: ListingContract.Interactor?)
    : ListingContract.Presenter {
    var view: ListingContract.View? = null
    val compositeDisposable = CompositeDisposable()

    override fun clear() {
        disposeComposites()
        interactor = null
        view = null
    }

    fun loadMoviesFromLocalDB(pageNum: String) {
        compositeDisposable.add(interactor?.getMovieModelFromLocalByPage(pageNum, object : ApiCallBack<MovieListModel> {
            override fun onSuccess(t: MovieListModel) {
                view?.onFetchingMovies(t)
            }

            override fun onError(throwable: Throwable) {
                Log.d("ERROR", throwable.message)
            }
        }))
    }

    override fun disposeComposites() {
        compositeDisposable.dispose()
    }


    override fun attachView(view: ListingContract.View) {
        this.view = view
    }
}