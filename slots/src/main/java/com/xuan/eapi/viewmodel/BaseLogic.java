package com.xuan.eapi.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.xuan.eapi.lifecycle.ILifeCycle;

/**
 * Author : xuan.
 * Date : 2018/5/9.
 * Description :the description of this file
 */

public abstract class BaseLogic implements ILifeCycle {
    protected Context mContext;

    public BaseLogic(Context context) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
