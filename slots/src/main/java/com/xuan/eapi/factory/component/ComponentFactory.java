package com.xuan.eapi.factory.component;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.IPresenterBind;
import com.xuan.eapi.Slots;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.context.ToolKitContext;


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
    private AdapterComponent adapter;


    @Override
    public Component createViewHolder(int type) {
        ViewInfo viewInfo = Slots.getInstance().obtainRule().obtainViewInfo(type);
        if (viewInfo == null) {
            return defaultViewHolder();
        }
        IComponentBind component = null;
        initFactory(viewInfo);
        if (!viewInfo.isAutoCreate()) {
            component = selfCreateComponent(viewInfo);
        } else {
            int viewType = viewInfo.getViewType();
            switch (viewType) {
                case 0:
                    component = viewFactory.createViewComponent(viewInfo);
                    break;
                case 1:
                    component = viewHolderFactory.createViewHolderComponent(viewInfo);
                    break;
            }
        }
        //组件mvp,注入Presenter到View中
        if (IPresenterBind.class.isAssignableFrom(viewInfo.getView())) {
            IPresenterBind presenterBind = (IPresenterBind) component;
            presenterBind.injectPresenter(tookContext.obtainPageLogic(viewInfo.getPresenter()));
        }
        return adapter.adapterComponent(component);
    }

    private void initFactory(ViewInfo viewInfo) {

        switch (viewInfo.getViewType()) {
            case 0:
                createViewFactory(tookContext);
                adapter = (AdapterComponent) viewFactory;
                break;
            case 1:
                createViewHolderFactory(tookContext);
                adapter = (AdapterComponent) viewHolderFactory;
                break;
        }
    }

    private IComponentBind selfCreateComponent(ViewInfo viewInfo) {
        return tookContext.createView(viewInfo.getId());
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
