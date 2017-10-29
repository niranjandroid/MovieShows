package com.niranjandroid.movieshows.ui.details

import com.niranjandroid.movieshows.data.model.Genre
import com.niranjandroid.movieshows.data.model.ImagesModel
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import com.niranjandroid.movieshows.ui.base.BaseContract
import io.reactivex.disposables.Disposable

/**
 * Created by Niranjan P on 10/24/2017.
 */

interface DetailsContract {

    interface View : BaseContract.FragmentView {
        fun onFetchingImages(images: MutableList<String>)
        fun onFetchingMovieDetails(t: MovieModel)
    }

    interface Presenter : BaseContract.Presenter {
        fun getImages(id: Long?)
        fun attachView(view : DetailsContract.View)
        fun getGenres(genres: List<Genre>?): String?
        fun getMovieDetails(id: Long?)
        fun getImagesFromImageModel(imagesModel: ImagesModel): MutableList<String>
    }

    interface Interactor {
        fun getImages(id: Long?, apiCallBack: ApiCallBack<ImagesModel>): Disposable?
        fun fetchMovieDetails(id: Long?, apiCallBack: ApiCallBack<MovieModel>): Disposable?
        fun saveMovieDetails(movieModel: MovieModel)
    }
}