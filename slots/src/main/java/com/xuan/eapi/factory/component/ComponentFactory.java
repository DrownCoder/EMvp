package com.xuan.eapi.factory.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.IPresenterBind;
import com.xuan.eapi.Slots;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.context.SlotContext;


/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :组件工厂
 */

public class ComponentFactory implements IComponentFactory {
    private SlotContext tookContext;
    private Context context;
    private ViewGroup parent;

    public ComponentFactory(Context context, ViewGroup parent) {
        this.context = context;
        this.parent = parent;
    }

    private IViewComponentFactory viewFactory;
    private IViewHolderComponentFactory viewHolderFactory;
    private IDFComponentFactory dfComponentFactory;
    private AdapterComponent adapter;


    @Override
    public Component createViewHolder(Context context, ViewGroup parent, int type) {
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
                case ViewInfo.TYPE_VIEW:
                    component = viewFactory.createViewComponent(viewInfo);
                    break;
                case ViewInfo.TYPE_HOLDER:
                    component = viewHolderFactory.createViewHolderComponent(viewInfo);
                    break;
                case ViewInfo.TYPE_COMPONENT:
                    component = dfComponentFactory.createViewHolder(viewInfo);
                    break;
            }
        }
        //组件mvp,注入Presenter到View中
        if (IPresenterBind.class.isAssignableFrom(viewInfo.getView())) {
            IPresenterBind presenterBind = (IPresenterBind) component;
            presenterBind.injectPresenter(tookContext.bindViewLogic(viewInfo.getPresenter()));
        }
        return adapter.adapterComponent(component);
    }

    private void initFactory(ViewInfo viewInfo) {
        AdapterComponent adapterComponent = null;
        switch (viewInfo.getViewType()) {
            case ViewInfo.TYPE_VIEW:
                adapterComponent = createViewFactory(tookContext);
                break;
            case ViewInfo.TYPE_HOLDER:
                adapterComponent = createViewHolderFactory(tookContext);
                break;
            case ViewInfo.TYPE_COMPONENT:
                adapterComponent = createComponentFactory();
                break;
        }
        adapter = adapterComponent;
    }

    /**
     * 继承Component的ViewHolder的创建工厂
     */
    private AdapterComponent createComponentFactory() {
        if (dfComponentFactory == null) {
            dfComponentFactory = new DFComponentFactory(context, parent);
        }
        return (AdapterComponent) dfComponentFactory;
    }

    private IComponentBind selfCreateComponent(ViewInfo viewInfo) {
        return tookContext.createView(viewInfo.getId());
    }

    private AdapterComponent createViewFactory(SlotContext slotContext) {
        if (viewFactory == null) {
            viewFactory = new ViewComponentFactory(slotContext);
        }
        return (AdapterComponent) viewFactory;
    }

    private AdapterComponent createViewHolderFactory(SlotContext slotContext) {
        if (viewHolderFactory == null) {
            viewHolderFactory = new ViewHolderComponentFactory(context, parent);
        }
        return (AdapterComponent) viewHolderFactory;
    }

    private Component defaultViewHolder() {
        return null;
    }
}
