package com.xuan.eapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :原生的ViewHolder转Component
 */

public class ComponentViewHolderAdapter extends ComponentAdapter {
    public ComponentViewHolderAdapter(Context context, RecyclerView.ViewHolder vh) {
        super(context, vh);
    }
}
