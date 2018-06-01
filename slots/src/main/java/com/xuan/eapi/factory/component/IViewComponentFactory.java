package com.xuan.eapi.factory.component;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IViewComponentFactory{
    Component createViewComponent(ViewInfo type);
}
