package com.niranjandroid.movieshows.base;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Niranjan P on 8/20/2017.
 */

public abstract class BaseAbstractFragment extends LifecycleFragment implements BaseContract.FragmentView {

    private BaseContract.ActivityView mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseContract.ActivityView) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null)
            getPresenter().clear();
    }

    public abstract BaseContract.Presenter getPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void showToast(String message) {
        if (mActivity != null)
            mActivity.showToast(message);
    }

    @Override
    public void showNetworkError() {
        if (mActivity != null)
            mActivity.showNetworkError();
    }

    @Override
    public void launchFragment(Fragment fragment, String tag) {
        if (mActivity != null)
            mActivity.navigateToFragment(fragment, tag);
    }
}
