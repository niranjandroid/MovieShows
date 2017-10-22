package com.niranjandroid.movieshows.ui.movielisting


import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.ui.base.BaseScope
import dagger.Module
import dagger.Provides

/**
 * Created by Niranjan P on 8/22/2017.
 */

@Module
class MovieListingModule {

    @Provides
    @BaseScope
    fun provideLoginInteractor(apiService: ApiService): MovieListingContract.Interactor {
        return MovieListingInteractorImpl(apiService)
    }

    @Provides
    @BaseScope
    fun provideLoginPresenter(interactor: MovieListingContract.Interactor): MovieListingContract.Presenter {
        return MovieListingPresenterImpl(interactor)
    }
}
