package com.niranjandroid.movieshows.ui.movielisting

import android.os.Bundle
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.ui.base.BaseAbstractActivity
import com.niranjandroid.movieshows.ui.base.BaseContract
import kotlinx.android.synthetic.main.activity_container.*

class ListingActivity : BaseAbstractActivity() {

    override val presenter: BaseContract.Presenter?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setSupportActionBar(toolbar);
        init()
    }

    override fun init() {
        navigateToFragment(ListingFragment.newInstance(), "", true)
    }
}
