package com.niranjandroid.movieshows.ui.details

import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.ui.base.BaseScope
import dagger.Module
import dagger.Provides

/**
 * Created by Niranjan P on 10/24/2017.
 */
@Module
class DetailsModule {

    @Provides
    @BaseScope
    fun provideInteractor(apiService: ApiService): DetailsContract.Interactor {
        return DetailsInteractorImpl(apiService)
    }

    @Provides
    @BaseScope
    fun providePresenter(interactor: DetailsContract.Interactor): DetailsContract.Presenter {
        return DetailsPresenterImpl(interactor)
    }
}
