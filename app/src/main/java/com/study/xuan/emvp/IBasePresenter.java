package com.study.xuan.emvp;

/**
 * Author : xuan.
 * Date : 2018/5/4.
 * Description :input the description of this file
 */

public interface IBasePresenter {
    void onDestroy();

    void onResume();

    void onStart();

    void onCreate();

    void onPause();

    void onStop();

    void onRestart();

    void onNewIntent();
}
