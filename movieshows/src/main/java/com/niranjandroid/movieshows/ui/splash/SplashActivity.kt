package com.niranjandroid.movieshows.ui.splash

import android.arch.lifecycle.LifecycleActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.niranjandroid.movieshows.R
import com.niranjandroid.movieshows.ui.listing.ListingActivity

class SplashActivity : LifecycleActivity() {
    private var splashTimeHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setViewToFullScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startupHandler()
    }

    protected fun setViewToFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun startupHandler() {
        splashTimeHandler = Handler()
        splashTimeHandler?.postDelayed({ launchHomeScreen() }, SPLASH_TIME.toLong())
    }

    private fun launchHomeScreen() {
        startActivity(Intent(this, ListingActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        splashTimeHandler = null
    }

    companion object {
        private val SPLASH_TIME = 2000
    }
}
