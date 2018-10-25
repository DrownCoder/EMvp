package com.xuan.eapi.factory;

import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :IComponentBind转Component
 */

public interface AdapterComponent {
    Component adapterComponent(IComponentBind componentBind);
}
