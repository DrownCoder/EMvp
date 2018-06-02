package com.xuan.eapi.factory.component;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :IComponentBind转Component
 */

public interface AdapterComponent {
    public Component adapterComponent(IComponentBind componentBind);
}
