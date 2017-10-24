package com.niranjandroid.movieshows.ui.details

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

    override fun disposeComposites() {
        compositeDisposable.dispose()
    }

}
