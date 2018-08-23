package com.xuan.eapi.component;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xuan.eapi.lifecycle.ILifeCycle;

/**
 * Author : xuan.
 * Date : 2018/8/22.
 * Description :实现生命周期的Component
 */

public abstract class LifeComponent extends Component implements ILifeCycle {
    public LifeComponent(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void onDestroy() {

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
