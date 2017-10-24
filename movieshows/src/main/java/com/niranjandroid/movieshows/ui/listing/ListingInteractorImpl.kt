package com.niranjandroid.movieshows.ui.listing

import com.niranjandroid.movieshows.data.model.Genres
import com.niranjandroid.movieshows.data.model.MovieListModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.data.network.RxApiHandler
import io.reactivex.disposables.Disposable

/**
 * Created by Niranjan P on 8/27/2017.
 */

class ListingInteractorImpl(var apiService: ApiService) : ListingContract.Interactor {
    override fun fetchPopularMovies(pageNum : String, apiCallBack: ApiCallBack<MovieListModel>): Disposable =
            RxApiHandler<MovieListModel>().handle(apiService.getPopularMovies(pageNum), apiCallBack)

    override fun fetchGenres(apiCallBack: ApiCallBack<Genres>): Disposable =
            RxApiHandler<Genres>().handle(apiService.getGenres(), apiCallBack)
}
