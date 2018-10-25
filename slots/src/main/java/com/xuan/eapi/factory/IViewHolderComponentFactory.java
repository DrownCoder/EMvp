package com.xuan.eapi.factory;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface IViewHolderComponentFactory {
    IComponentBind createViewHolderComponent(ViewInfo type);
}
