package com.xuan.eapi.factory;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/9/4.
 * Description :Component的工厂
 */

public interface IDFComponentFactory {
    Component createViewHolder(ViewInfo viewInfo);
}
