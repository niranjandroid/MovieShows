package com.niranjandroid.movieshows.ui.details

import com.niranjandroid.movieshows.data.database.MovieDao
import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.ui.base.BaseScope
import com.niranjandroid.movieshows.ui.base.PostersHorizontalAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Niranjan P on 10/24/2017.
 */
@Module
class DetailsModule {

    @Provides
    @BaseScope
    fun provideInteractor(apiService: ApiService, movieDao: MovieDao): DetailsContract.Interactor {
        return DetailsInteractorImpl(apiService, movieDao)
    }

    @Provides
    @BaseScope
    fun providePresenter(interactor: DetailsContract.Interactor): DetailsContract.Presenter {
        return DetailsPresenterImpl(interactor)
    }

    @Provides
    @BaseScope
    fun provideHorizontalAdapter(): PostersHorizontalAdapter {
        return PostersHorizontalAdapter()
    }

    @Provides
    @BaseScope
    @Named("cast")
    fun provideCastAdapter(): ImageHorizontalAdapter {
        return ImageHorizontalAdapter()
    }

    @Provides
    @BaseScope
    @Named("crew")
    fun provideCrewAdapter(): ImageHorizontalAdapter {
        return ImageHorizontalAdapter()
    }


    @Provides
    @BaseScope
    @Named("trailers")
    fun provideTrailersAdapter(): ImageHorizontalAdapter {
        return ImageHorizontalAdapter()
    }


}
