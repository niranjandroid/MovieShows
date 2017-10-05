package com.niranjandroid.movieshows.splash;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.niranjandroid.movieshows.R;

public class SplashActivity extends LifecycleActivity {

    private static final int SPLASH_TIME = 2000;
    private Handler splashTimeHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setViewToFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startupHandler();
    }

    protected void setViewToFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startupHandler() {
        splashTimeHandler = new Handler();
        splashTimeHandler.postDelayed(() -> launchHomeScreen(), SPLASH_TIME);
    }

    private void launchHomeScreen() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashTimeHandler = null;
    }
}
