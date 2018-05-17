package com.study.xuan.emvp.factory;

import android.content.Context;

import com.study.xuan.emvp.vh.Component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IComponentFactory {
    Component createViewHolder(int type);

    void createViewFactory(Context context);
}
