package com.niranjandroid.movieshows.base

import android.arch.lifecycle.LifecycleFragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by Niranjan P on 8/20/2017.
 */

abstract class BaseAbstractFragment : LifecycleFragment(), BaseContract.FragmentView {

    private var mActivity: BaseContract.ActivityView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseContract.ActivityView?
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.clear()
    }

    abstract val presenter: BaseContract.Presenter?

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun showToast(message: String) {
        mActivity?.showToast(message)
    }

    override fun showNetworkError() {
        mActivity?.showNetworkError()
    }

    override fun launchFragment(fragment: Fragment, tag: String) {
        mActivity?.navigateToFragment(fragment, tag)
    }
}
