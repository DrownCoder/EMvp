package com.xuan.eapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View转ViewHolder
 */

public class ComponentViewHolderAdapter extends Component {
    private IComponentBind root;

    public ComponentViewHolderAdapter(Context context, RecyclerView.ViewHolder vh) {
        super(context, vh.itemView);
        root = (IComponentBind) vh;
    }

    @Override
    public void onBind(int pos, Object item) {
        root.bind(item);
    }

}
