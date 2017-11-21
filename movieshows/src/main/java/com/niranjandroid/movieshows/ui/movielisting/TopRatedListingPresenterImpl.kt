package com.niranjandroid.movieshows.ui.movielisting

import android.util.Log
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack

/**
 * Created by Niranjan P on 8/20/2017.
 */

class TopRatedListingPresenterImpl(private var interactor: ListingContract.Interactor?)
    : BaseListingPresenter(interactor) {


    override fun initMoviesList() {
        compositeDisposable.add(interactor?.fetchTopRatedMovies("1", object : ApiCallBack<MovieListModel> {
            override fun onSuccess(movieListModel: MovieListModel) {
                view?.onFetchingMovies(movieListModel)
                interactor?.saveMovieList(movieListModel)
            }

            override fun onError(throwable: Throwable) {
                Log.d("Niranjan", throwable.message + "")
                loadMoviesFromLocalDB("1")
            }
        }))
    }

    override fun loadMovies(pageNum: Int) {
        compositeDisposable.add(interactor?.fetchTopRatedMovies(pageNum.toString(), object : ApiCallBack<MovieListModel> {
            override fun onSuccess(movieListModel: MovieListModel) {
                view?.updateMovies(movieListModel)
                interactor?.saveMovieList(movieListModel)
            }

            override fun onError(throwable: Throwable) {
                loadMoviesFromLocalDB(pageNum.toString());
            }
        }))
    }

}