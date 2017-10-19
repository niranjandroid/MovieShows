package com.niranjandroid.movieshows.movielisting

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Niranjan P on 8/20/2017.
 */

class MovieListingPresenterImpl(private var interactor: MovieListingContract.Interactor?)
    : MovieListingContract.Presenter {
    private var view: MovieListingContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        disposeComposites()
        interactor = null
        view = null
    }

    override fun disposeComposites() {
        compositeDisposable.dispose()
    }


    override fun attachView(view: MovieListingContract.View) {
        this.view = view
    }
}
