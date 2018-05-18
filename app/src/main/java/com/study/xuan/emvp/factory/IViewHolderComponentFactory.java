package com.study.xuan.emvp.factory;

import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface IViewHolderComponentFactory extends ReflectCreate<RecyclerView.ViewHolder>{
    Component createViewHolderComponent(ViewInfo type);

    RecyclerView.ViewHolder reflectCreate(Class<?> clazz);
}
