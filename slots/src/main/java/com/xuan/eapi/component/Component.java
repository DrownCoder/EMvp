package com.xuan.eapi.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :基础的ViewHolder，无生命周期的感知
 */

public class Component extends RecyclerView.ViewHolder implements IBaseVH {
    protected Context context;
    private View root;

    public Component(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.root = itemView;
    }

    @Override
    public void onBind(int pos, Object item) {

    }

    @Override
    public void onUnBind() {

    }
}
