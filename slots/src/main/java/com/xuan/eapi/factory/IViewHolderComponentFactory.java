package com.xuan.eapi.factory;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :继承原生的ViewHolder的工厂，需要继承IComponentBind
 */

public interface IViewHolderComponentFactory {
    IComponentBind createViewHolderComponent(ViewInfo type);
}
