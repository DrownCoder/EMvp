package com.xuan.eapi.factory.component;

import android.support.v7.widget.RecyclerView;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface IViewHolderComponentFactory {
    IComponentBind createViewHolderComponent(ViewInfo type);

    RecyclerView.ViewHolder reflectCreate(Class<?> clazz);
}
