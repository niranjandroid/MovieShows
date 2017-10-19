package com.niranjandroid.movieshows.movielisting

import com.niranjandroid.movieshows.base.BaseContract

/**
 * Created by Niranjan P on 8/20/2017.
 */

interface MovieListingContract {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun attachView(view: MovieListingContract.View)
    }

    interface Interactor
}
