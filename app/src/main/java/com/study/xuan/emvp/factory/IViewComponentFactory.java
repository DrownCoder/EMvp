package com.study.xuan.emvp.factory;

import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.vh.Component;
import com.study.xuan.emvp.widget.IWidget;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IViewComponentFactory {
    Component createViewComponent(ViewInfo type);

    IWidget reflectCreate(Class<?> clazz);
}
