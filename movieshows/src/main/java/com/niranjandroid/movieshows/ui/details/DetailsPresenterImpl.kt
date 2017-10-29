package com.niranjandroid.movieshows.ui.details

import android.util.Log
import com.niranjandroid.movieshows.data.model.Genre
import com.niranjandroid.movieshows.data.model.ImagesModel
import com.niranjandroid.movieshows.data.model.MovieModel
import com.niranjandroid.movieshows.data.network.ApiCallBack
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Niranjan P on 10/24/2017.
 */

class DetailsPresenterImpl(private var interactor: DetailsContract.Interactor?) : DetailsContract.Presenter {

    private var view: DetailsContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        disposeComposites()
        interactor = null
        view = null
    }

    override fun attachView(view: DetailsContract.View) {
        this.view = view
    }

    override fun disposeComposites() {
        compositeDisposable.dispose()
    }


    override fun getImages(id: Long?) {
        compositeDisposable.add(interactor?.getImages(id, object : ApiCallBack<ImagesModel>{
            override fun onSuccess(t: ImagesModel) {
                var images = getImagesFromImageModel(t)
                view?.onFetchingImages(images)
            }

            override fun onError(throwable: Throwable) {
                Log.d("ERROR", throwable.message)
            }
        }))
    }

    override fun getMovieDetails(id: Long?) {
        compositeDisposable.add(interactor?.fetchMovieDetails(id, object : ApiCallBack<MovieModel>{
            override fun onSuccess(t: MovieModel) {
                view?.onFetchingMovieDetails(t)
                interactor?.saveMovieDetails(t)
            }

            override fun onError(throwable: Throwable) {
                Log.d("ERROR", throwable.message)
            }
        }))
    }

    override fun getImagesFromImageModel(imagesModel: ImagesModel) : MutableList<String> {
        var images: MutableList<String> = ArrayList()
        imagesModel.backdrops.let {
            for(image in imagesModel.backdrops!!) {
                image.mFilePath?.let {images.add(image?.mFilePath!!) }
            }
        }
        imagesModel.posters.let {
            for(image in imagesModel.posters!!) {
                image.mFilePath?.let {images.add(image?.mFilePath!!) }
            }
        }
        return images
    }

    override fun getGenres(genres: List<Genre>?): String? {
        var genresVal = ""
        genres?.let {
            var i = 0;
            for(genre in genres!!) {
                when(i++) {
                    0 -> genresVal = genre?.name!!
                    genres?.size - 1 -> genresVal = "$genresVal and ${genre?.name}"
                    else -> genresVal = "$genresVal, ${genre?.name}"
                }
            }
        }
        return genresVal
    }

}
