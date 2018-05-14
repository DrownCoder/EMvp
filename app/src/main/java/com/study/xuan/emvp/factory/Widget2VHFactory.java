package com.study.xuan.emvp.factory;

import android.view.View;

import com.study.xuan.emvp.adapter.ViewHolderAdapter;
import com.study.xuan.emvp.vh.EViewHolder;
import com.study.xuan.emvp.widget.IWidget;
import com.study.xuan.emvp.widget.UserInfoLayout;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class Widget2VHFactory implements IWidget2VHFactory {
    @Override
    public EViewHolder onTranWidget2VH(int type) {
        IWidget view = null;
        switch (type) {
            default:
                view = new UserInfoLayout();
        }
        return new ViewHolderAdapter((View) view);
    }
}
