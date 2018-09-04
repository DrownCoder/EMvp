package com.xuan.eapi.factory.component;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface IViewHolderComponentFactory {
    IComponentBind createViewHolderComponent(ViewInfo type);
}
