package com.niranjandroid.movieshows.ui.listing


import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.ui.base.BaseScope
import dagger.Module
import dagger.Provides

/**
 * Created by Niranjan P on 8/22/2017.
 */

@Module
class ListingModule {

    @Provides
    @BaseScope
    fun provideInteractor(apiService: ApiService): ListingContract.Interactor {
        return ListingInteractorImpl(apiService)
    }

    @Provides
    @BaseScope
    fun providePresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return ListingPresenterImpl(interactor)
    }
}
