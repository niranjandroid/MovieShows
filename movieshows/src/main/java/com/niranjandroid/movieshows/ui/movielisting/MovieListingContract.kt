package com.niranjandroid.movieshows.ui.movielisting

import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import com.niranjandroid.movieshows.ui.base.BaseContract
import io.reactivex.disposables.Disposable

/**
 * Created by Niranjan P on 8/20/2017.
 */

interface MovieListingContract {
    interface View : BaseContract.FragmentView {
        fun updateMovies(movieListModel: MovieListModel)
    }

    interface Presenter : BaseContract.Presenter {
        fun attachView(view: MovieListingContract.View)
        fun initMoviesList()
    }

    interface Interactor {
        fun fetchPopularMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>) : Disposable
    }
}
