package com.niranjandroid.movieshows.ui.listing

import android.util.Log
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Niranjan P on 8/20/2017.
 */

class ListingPresenterImpl(private var interactor: ListingContract.Interactor?)
    :  ListingContract.Presenter {
    private var view: ListingContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        disposeComposites()
        interactor = null
        view = null
    }

    override fun initMoviesList() {
        compositeDisposable.add(interactor?.fetchPopularMovies("1", object : ApiCallBack<MovieListModel> {
            override fun onSuccess(movieListModel: MovieListModel) {
                view?.onFetchingMovies(movieListModel)
            }

            override fun onError(throwable: Throwable) {
                Log.d("Niranjan", throwable?.message +"")
                //handle error through rx bus
            }
        }))
    }

    override fun loadMovies(pageNum: Int) {
        compositeDisposable.add(interactor?.fetchPopularMovies(pageNum.toString(), object : ApiCallBack<MovieListModel> {
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
    }


    override fun attachView(view: ListingContract.View) {
        this.view = view
    }
}