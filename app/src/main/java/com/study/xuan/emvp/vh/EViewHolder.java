package com.study.xuan.emvp.vh;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.study.xuan.emvp.model.Floor;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class EViewHolder extends RecyclerView.ViewHolder implements IBaseVH, LifecycleObserver {
    public EViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int pos, Floor item) {

    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void onCreate(Floor item) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onActivityCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onActivityDestroy() {

    }


}
