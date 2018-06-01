package com.xuan.eapi.factory.component;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.Slots;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.helper.ToolKitContext;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :组件工厂
 */

public class ComponentFactory implements IComponentFactory {
    private ToolKitContext tookContext;

    public ComponentFactory(ToolKitContext toolKitContext) {
        this.tookContext = toolKitContext;
    }

    private IViewComponentFactory viewFactory;
    private IViewHolderComponentFactory viewHolderFactory;

    @Override
    public Component createViewHolder(int type) {
        ViewInfo viewInfo = Slots.getInstance().obtainRule().obtainViewInfo(type);
        if (viewInfo == null) {
            return defaultViewHolder();
        }
        int viewType = viewInfo.getViewType();
        switch (viewType) {
            case 0:
                createViewFactory(tookContext);
                return viewFactory.createViewComponent(viewInfo);
            case 1:
                createViewHolderFactory(tookContext);
                return viewHolderFactory.createViewHolderComponent(viewInfo);
        }
        return defaultViewHolder();
    }

    @Override
    public void createViewFactory(ToolKitContext toolKitContext) {
        if (viewFactory == null) {
            viewFactory = new ViewComponentFactory(toolKitContext);
        }
    }

    @Override
    public void createViewHolderFactory(ToolKitContext toolKitContext) {
        if (viewHolderFactory == null) {
            viewHolderFactory = new ViewHolderComponentFactory(toolKitContext);
        }

    }

    private Component defaultViewHolder() {
        return null;
    }

}
