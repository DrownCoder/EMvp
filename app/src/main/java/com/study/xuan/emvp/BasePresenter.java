package com.study.xuan.emvp;

import android.content.Context;

/**
 * Author : xuan.
 * Date : 2018/5/9.
 * Description :the description of this file
 */

public abstract class BasePresenter implements IBasePresenter {
    protected Context mContext;

    public BasePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onDestroy() {
        mContext = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onNewIntent() {

    }
}
