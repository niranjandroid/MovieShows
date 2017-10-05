package com.niranjandroid.movieshows.base;

import android.support.v4.app.Fragment;

/**
 * Created by Niranjan P on 8/20/2017.
 */

public interface BaseContract {
    interface View {
        void init();

        void showToast(String message);

        void showNetworkError();
    }

    interface ActivityView extends View {
        void navigateToFragment(Fragment fragment, String tag);

        void navigateToFragment(Fragment fragment, String tag, boolean noHistory);

        void navigateToFragment(Fragment fragment, String tag, boolean noHistory, boolean clearStack);
    }

    interface FragmentView extends View {
        void launchFragment(Fragment fragment, String tag);
    }

    interface Presenter {
        void clear();

        void disposeComposites();
    }
}
