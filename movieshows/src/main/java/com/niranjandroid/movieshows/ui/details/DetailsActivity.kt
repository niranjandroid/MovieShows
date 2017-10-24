package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.ui.base.BaseAbstractActivity
import com.niranjandroid.movieshows.ui.base.BaseContract

class DetailsActivity : BaseAbstractActivity() {

    override val presenter: BaseContract.Presenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewToFullScreen()
        setContentView(R.layout.activity_container)
        (findViewById<View>(R.id.toolbar) as Toolbar)?.visibility = View.GONE
        init()
    }

    private fun setViewToFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun init() {
        navigateToFragment(DetailsFragment.newInstance(intent?.getStringExtra(Constants.MOVIE_OBJ)), "", true)
    }
}