package com.xuan.eapi;

/**
 * Author : xuan.
 * Date : 2018/5/4.
 * Description :生命周期Activity
 */

public interface ILifeCycle {
    void onDestroy();

    void onResume();

    void onStart();

    void onCreate();

    void onPause();

    void onStop();

    void onRestart();

    void onNewIntent();
}
