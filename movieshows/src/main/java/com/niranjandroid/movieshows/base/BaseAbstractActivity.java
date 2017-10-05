package com.niranjandroid.movieshows.base;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.niranjandroid.movieshows.R;

/**
 * Created by Niranjan P on 8/20/2017.
 */

public abstract class BaseAbstractActivity extends LifecycleActivity implements BaseContract.ActivityView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null)
            getPresenter().clear();
    }

    public abstract BaseContract.Presenter getPresenter();

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }

    @Override
    public void showNetworkError() {

    }


    @Override
    public void navigateToFragment(Fragment fragment, String tag) {
        navigateToFragment(fragment, tag, false, false);
    }

    @Override
    public void navigateToFragment(Fragment fragment, String tag, boolean noHistory) {
        navigateToFragment(fragment, tag, noHistory, false);
    }

    @Override
    public void navigateToFragment(Fragment fragment, String tag, boolean noHistory, boolean clearStack) {
        FragmentTransaction beginTransaction = getSupportFragmentManager()
            .beginTransaction();

        if (clearStack) {
            getSupportFragmentManager().popBackStack(null, FragmentManager
                .POP_BACK_STACK_INCLUSIVE);
        }

        if (!noHistory) {
            beginTransaction.addToBackStack(tag);
        }
        beginTransaction.replace(R.id.container, fragment, tag);
        beginTransaction.commit();
        invalidateOptionsMenu();
    }
}
