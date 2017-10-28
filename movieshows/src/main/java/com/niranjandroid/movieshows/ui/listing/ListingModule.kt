package com.niranjandroid.movieshows.ui.listing


import com.niranjandroid.movieshows.data.database.GenreDao
import com.niranjandroid.movieshows.data.database.MovieListDao
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
    fun provideInteractor(apiService: ApiService,
                          movieListDao: MovieListDao, genreDao: GenreDao): ListingContract.Interactor {
        return ListingInteractorImpl(apiService, movieListDao, genreDao)
    }

    @Provides
    @BaseScope
    fun providePresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return ListingPresenterImpl(interactor)
    }

    @Provides
    @BaseScope
    fun provideListingAdapter(): ListingAdapter {
        return ListingAdapter()
    }
}
