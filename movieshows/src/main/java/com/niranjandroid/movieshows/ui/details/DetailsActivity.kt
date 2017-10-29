package com.niranjandroid.movieshows.ui.details

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import com.niranjandroid.movieshows.Constants
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.ui.base.BaseAbstractActivity
import com.niranjandroid.movieshows.ui.base.BaseContract
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : BaseAbstractActivity() {

    override val presenter: BaseContract.Presenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewToFullScreen()
        setContentView(R.layout.activity_details)
        //toolbar.visibility = View.GONE
        init()
    }

    private fun setViewToFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun init() {
        setToolbar()
        navigateToFragment(DetailsFragment.newInstance(intent?.getStringExtra(Constants.MOVIE_OBJ)), "", true)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.background = null
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}