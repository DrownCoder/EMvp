package com.xuan.eapi.factory;

import android.content.Context;
import android.view.ViewGroup;

import com.xuan.eapi.component.Component;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the interface of component factory，创建Component的工厂
 */

public interface IComponentFactory {
    Component createViewHolder(Context context, ViewGroup parent, int type);
}
