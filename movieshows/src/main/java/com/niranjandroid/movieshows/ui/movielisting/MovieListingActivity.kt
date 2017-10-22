package com.niranjandroid.movieshows.ui.movielisting

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.ui.base.BaseAbstractActivity
import com.niranjandroid.movieshows.ui.base.BaseContract

class MovieListingActivity : BaseAbstractActivity() {

    override val presenter: BaseContract.Presenter?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_listing)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);
        init()
    }

    override fun init() {
        navigateToFragment(MovieListingFragment.newInstance(), "")
    }
}
