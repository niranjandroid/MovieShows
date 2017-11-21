package com.niranjandroid.movieshows.ui.movielisting


import com.niranjandroid.movieshows.data.database.MovieListDao
import com.niranjandroid.movieshows.data.network.ApiService
import com.niranjandroid.movieshows.ui.base.BaseScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Niranjan P on 8/22/2017.
 */

@Module
class ListingModule {

    @Provides
    @BaseScope
    fun provideInteractor(apiService: ApiService,
                          movieListDao: MovieListDao): ListingContract.Interactor {
        return ListingInteractorImpl(apiService, movieListDao)
    }

    @Provides
    @BaseScope
    @Named("top_rated")
    fun provideTopRatedPresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return TopRatedListingPresenterImpl(interactor)
    }
    @Provides
    @BaseScope
    @Named("popular")
    fun providePopularPresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return PopularListingPresenterImpl(interactor)
    }
    @Provides
    @BaseScope
    @Named("now_playing")
    fun provideNowPlayingPresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return NowPlayingListingPresenterImpl(interactor)
    }
    @Provides
    @BaseScope
    @Named("upcoming")
    fun provideUpcomingPresenter(interactor: ListingContract.Interactor): ListingContract.Presenter {
        return UpcomingListingPresenterImpl(interactor)
    }
    @Provides
    @BaseScope
    fun provideListingAdapter(): ListingAdapter {
        return ListingAdapter()
    }
}
