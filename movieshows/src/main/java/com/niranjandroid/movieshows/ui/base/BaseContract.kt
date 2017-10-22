package com.niranjandroid.movieshows.ui.base

import android.support.v4.app.Fragment

/**
 * Created by Niranjan P on 8/20/2017.
 */

interface BaseContract {
    interface View {
        fun init()

        fun showToast(message: String)

        fun showNetworkError()
    }

    interface ActivityView : View {
        fun navigateToFragment(fragment: Fragment, tag: String)

        fun navigateToFragment(fragment: Fragment, tag: String, noHistory: Boolean)

        fun navigateToFragment(fragment: Fragment, tag: String, noHistory: Boolean, clearStack: Boolean)
    }

    interface FragmentView : View {
        fun launchFragment(fragment: Fragment, tag: String)
    }

    interface Presenter {
        fun clear()

        fun disposeComposites()
    }
}
