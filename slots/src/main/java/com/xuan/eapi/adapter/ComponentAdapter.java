package com.xuan.eapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.helper.event.InjectCallback;

/**
 * Author : xuan.
 * Date : 2018/9/1.
 * Description :the description of this file
 */

public class ComponentAdapter extends Component implements InjectCallback {
    private IComponentBind root;
    private View.OnClickListener onClickListener;

    public ComponentAdapter(Context context, View itemView) {
        super(context, itemView);
        root = (IComponentBind) itemView;
    }

    public ComponentAdapter(Context context, RecyclerView.ViewHolder viewHolder) {
        super(context, viewHolder.itemView);
        root = (IComponentBind) viewHolder;
    }

    @Override
    public void onBind(int pos, Object item) {
        if (InjectCallback.class.isAssignableFrom(root.getClass())) {
            ((InjectCallback) root).injectCallback(onClickListener);
        }
        root.onBind(pos, item);
    }

    @Override
    public void injectCallback(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
