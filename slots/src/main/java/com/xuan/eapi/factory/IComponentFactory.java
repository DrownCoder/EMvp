package com.xuan.eapi.factory;

import com.xuan.eapi.helper.ToolKitContext;
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
