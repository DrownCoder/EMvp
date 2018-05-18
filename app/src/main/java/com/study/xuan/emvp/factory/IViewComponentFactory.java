package com.study.xuan.emvp.factory;

import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.widget.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IViewComponentFactory extends ReflectCreate<IComponentBind>{
    Component createViewComponent(ViewInfo type);

    IComponentBind reflectCreate(Class<?> clazz);
}
