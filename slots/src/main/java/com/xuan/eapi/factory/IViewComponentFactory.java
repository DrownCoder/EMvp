package com.xuan.eapi.factory;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :自定义View，继承IComponentBind接口的工厂
 */

public interface IViewComponentFactory {
    IComponentBind createViewComponent(ViewInfo type);
}
