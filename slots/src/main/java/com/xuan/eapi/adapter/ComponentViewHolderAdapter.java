package com.xuan.eapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.helper.event.InjectCallback;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View转ViewHolder
 */

public class ComponentViewHolderAdapter extends ComponentAdapter {
    public ComponentViewHolderAdapter(Context context, RecyclerView.ViewHolder vh) {
        super(context, vh);
    }
}
