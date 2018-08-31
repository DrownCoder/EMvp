package com.xuan.eapi.lifecycle;

import android.content.Intent;

/**
 * Author : xuan.
 * Date : 2018/8/31.
 * Description :只需要感知onDestroy的adapter
 */

public class GCAdapter implements ILifeCycle{
    private IGC gc;

    public GCAdapter(IGC gc) {
        this.gc = gc;
    }

    @Override
    public void onDestroy() {
        if (gc != null) {
            gc.onDestroy();
        }
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
