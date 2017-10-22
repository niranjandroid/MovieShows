package com.niranjandroid.movieshows.ui.movielisting

import android.util.Log
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Niranjan P on 8/20/2017.
 */

class MovieListingPresenterImpl(private var interactor: MovieListingContract.Interactor?)
    :  MovieListingContract.Presenter {
    private var view: MovieListingContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        disposeComposites()
        interactor = null
        view = null
    }

    override fun initMoviesList() {
        compositeDisposable.add(interactor?.fetchPopularMovies("1", object : ApiCallBack<MovieListModel> {
            override fun onSuccess(movieListModel: MovieListModel) {
                view?.updateMovies(movieListModel)
            }

            override fun onError(throwable: Throwable) {
                Log.d("Niranjan", throwable?.message +"")
                //handle error through rx bus
            }
        }))
    }

    override fun disposeComposites() {
        compositeDisposable.dispose()
        view = null
    }


    override fun attachView(view: MovieListingContract.View) {
        this.view = view
    }
}
