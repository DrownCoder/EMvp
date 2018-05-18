package com.study.xuan.emvp.factory;

import android.content.Context;

import com.study.xuan.emvp.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the interface of component factory
 */

public interface IComponentFactory {
    Component createViewHolder(int type);

    void createViewFactory(Context context);

    void createViewHolderFactory(Context context);
}
