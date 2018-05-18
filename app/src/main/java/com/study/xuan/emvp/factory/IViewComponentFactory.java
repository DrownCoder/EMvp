package com.study.xuan.emvp.factory;

import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.widget.IWidget;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IViewComponentFactory extends ReflectCreate<IWidget>{
    Component createViewComponent(ViewInfo type);

    IWidget reflectCreate(Class<?> clazz);
}
