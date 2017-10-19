package com.niranjandroid.movieshows.base

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.niranjandroid.movieshows.R

/**
 * Created by Niranjan P on 8/20/2017.
 */

abstract class BaseAbstractActivity : LifecycleActivity(), BaseContract.ActivityView {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter?.clear()
    }

    abstract val presenter: BaseContract.Presenter?

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }

    override fun showNetworkError() {

    }


    override fun navigateToFragment(fragment: Fragment, tag: String) {
        navigateToFragment(fragment, tag, false, false)
    }

    override fun navigateToFragment(fragment: Fragment, tag: String, noHistory: Boolean) {
        navigateToFragment(fragment, tag, noHistory, false)
    }

    override fun navigateToFragment(fragment: Fragment, tag: String, noHistory: Boolean, clearStack: Boolean) {
        val beginTransaction = supportFragmentManager
                .beginTransaction()

        if (clearStack) {
            supportFragmentManager.popBackStack(null, FragmentManager
                    .POP_BACK_STACK_INCLUSIVE)
        }

        if (!noHistory) {
            beginTransaction.addToBackStack(tag)
        }
        beginTransaction.replace(R.id.container, fragment, tag)
        beginTransaction.commit()
        invalidateOptionsMenu()
    }
}
