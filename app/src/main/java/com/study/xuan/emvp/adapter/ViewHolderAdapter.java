package com.study.xuan.emvp.adapter;

import android.view.View;

import com.study.xuan.emvp.vh.EViewHolder;
import com.study.xuan.emvp.widget.IWidget;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View转ViewHolder
 */

public class ViewHolderAdapter extends EViewHolder{
    private IWidget root;

    public ViewHolderAdapter(View itemView) {
        super(itemView);
        root = (IWidget) itemView;
    }

    @Override
    public void onBind(int pos, Object item) {
        root.bind(item);
    }

}
