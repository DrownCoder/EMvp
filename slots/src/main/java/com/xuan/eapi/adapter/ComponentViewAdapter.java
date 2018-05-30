package com.xuan.eapi.adapter;

import android.view.View;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View转ViewHolder
 */

public class ComponentViewAdapter extends Component {
    private IComponentBind root;

    public ComponentViewAdapter(View itemView) {
        super(itemView);
        root = (IComponentBind) itemView;
    }

    @Override
    public void onBind(int pos, Object item) {
        root.bind(item);
    }

}
