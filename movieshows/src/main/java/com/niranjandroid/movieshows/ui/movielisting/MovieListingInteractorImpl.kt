package com.niranjandroid.movieshows.ui.movielisting

import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.data.network.RxApiHandler
import io.reactivex.disposables.Disposable

/**
 * Created by Niranjan P on 8/27/2017.
 */

class MovieListingInteractorImpl(var apiService: ApiService) : MovieListingContract.Interactor {
    override fun fetchPopularMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getPopularMovies(pageNum), apiCallBack)

}
