package com.niranjandroid.movieshows.movielisting;

import com.niranjandroid.movieshows.base.BaseContract;

/**
 * Created by Niranjan P on 8/20/2017.
 */

public interface MovieListingContract {
    interface View extends BaseContract.View{

    }

    interface Presenter extends BaseContract.Presenter {
        void attachView(MovieListingContract.View view);
    }

    interface Interactor {
    }
}
