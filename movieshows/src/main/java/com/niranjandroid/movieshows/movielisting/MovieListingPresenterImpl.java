package com.niranjandroid.movieshows.movielisting;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Niranjan P on 8/20/2017.
 */

public class MovieListingPresenterImpl implements MovieListingContract.Presenter {
    private MovieListingContract.Interactor interactor;
    private MovieListingContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MovieListingPresenterImpl(MovieListingContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void clear() {
        disposeComposites();
        interactor = null;
        view = null;
    }

    @Override
    public void disposeComposites() {
        compositeDisposable.dispose();
    }


    @Override
    public void attachView(MovieListingContract.View view) {
        this.view = view;
    }
}
