package com.study.xuan.emvp.component;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class Component extends RecyclerView.ViewHolder implements IBaseVH, LifecycleObserver {
    private View viewRoot;
    public Component(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int pos, Object item) {

    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void onCreate(Object item) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onActivityCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onActivityDestroy() {

    }


}
