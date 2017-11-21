package com.niranjandroid.movieshows.ui.details

import android.util.Log
import com.niranjandroid.movieshows.data.model.*
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
                view?.updatePosters(images)
            }

            override fun onError(throwable: Throwable) {
                Log.d("ERROR", throwable.message)
            }
        }))
    }

    override fun getMovieDetails(id: Long?) {
        compositeDisposable.add(interactor?.fetchMovieDetails(id, object : ApiCallBack<MovieModel>{
            override fun onSuccess(t: MovieModel) {
                var movieModel = getProcessedMovieModel(t)
                view?.onFetchingMovieDetails(movieModel)
                interactor?.saveMovieDetails(movieModel)
            }

            override fun onError(throwable: Throwable) {
                Log.d("ERROR", throwable.message)
            }
        }))
    }

    private fun getProcessedMovieModel(movieModel: MovieModel) : MovieModel {
        var castList : MutableList<CastModel> = ArrayList()
        var crewList : MutableList<Crew> = ArrayList()
        for(cast in movieModel.casts.castList) {
            cast.profilePath?.let { castList.add(cast) }
        }

        for (crew in movieModel.casts.crewList) {
            crew.profilePath?.let { crewList.add(crew) }
        }
        movieModel.casts.castList = castList
        movieModel.casts.crewList = crewList
        return movieModel
    }

    override fun getImagesFromImageModel(imagesModel: ImagesModel) : MutableList<String> {
        var images: MutableList<String> = ArrayList()
        imagesModel.backdrops.let {
            for(image in imagesModel.backdrops!!) {
                image.mFilePath?.let {images.add(image.mFilePath!!) }
            }
        }
        imagesModel.posters.let {
            for(image in imagesModel.posters!!) {
                image.mFilePath?.let {images.add(image.mFilePath!!) }
            }
        }
        return images
    }

    override fun getGenres(genres: List<Genre>?): String? {
        var genresVal = ""
        genres?.let {
            var i = 0;
            for(genre in genres) {
                when(i++) {
                    0 -> genresVal = genre.name!!
                    genres.size - 1 -> genresVal = "$genresVal and ${genre.name}"
                    else -> genresVal = "$genresVal, ${genre.name}"
                }
            }
        }
        return genresVal
    }

}
