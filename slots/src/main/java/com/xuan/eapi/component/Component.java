package com.xuan.eapi.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xuan.eapi.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :基础的ViewHolder，无生命周期的感知
 */

public abstract class Component<T> extends RecyclerView.ViewHolder implements IComponentBind<T> {
    protected Context context;
    private View root;

    public Component(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.root = itemView;
    }

    public abstract void onBind(int pos, T item);

    @Override
    public void onUnBind() {

    }
}
