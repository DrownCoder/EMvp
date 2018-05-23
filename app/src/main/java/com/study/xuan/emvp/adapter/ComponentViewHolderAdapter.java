package com.study.xuan.emvp.adapter;

import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View转ViewHolder
 */

public class ComponentViewHolderAdapter extends Component {
    private IComponentBind root;

    public ComponentViewHolderAdapter(RecyclerView.ViewHolder vh) {
        super(vh.itemView);
        root = (IComponentBind) vh;
    }

    @Override
    public void onBind(int pos, Object item) {
        root.bind(item);
    }

}
