package com.study.xuan.emvp.adapter;

import android.view.View;

import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.widget.IComponentBind;

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
