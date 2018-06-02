package com.xuan.eapi.factory.component;

import com.xuan.eapi.context.ToolKitContext;
import com.xuan.eapi.component.Component;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the interface of component factory
 */

public interface IComponentFactory {
    Component createViewHolder(int type);

    void createViewFactory(ToolKitContext toolKitContext);

    void createViewHolderFactory(ToolKitContext toolKitContext);
}