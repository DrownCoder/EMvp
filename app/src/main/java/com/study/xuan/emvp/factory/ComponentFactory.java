package com.study.xuan.emvp.factory;

import android.content.Context;
import android.view.ViewGroup;

import com.study.xuan.emvp.BasePresenter;
import com.study.xuan.emvp.ComponentRule;
import com.xuan.annotation.ViewInfo;
import com.study.xuan.emvp.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :组件工厂
 */

public class ComponentFactory implements IComponentFactory {
    private Context mContext;
    private ViewGroup mParentRoot;
    private BasePresenter mPresenter;

    public ComponentFactory(Context context, BasePresenter presenter, ViewGroup parent) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mParentRoot = parent;
    }

    private IViewComponentFactory viewFactory;
    private IViewHolderComponentFactory viewHolderFactory;

    @Override
    public Component createViewHolder(int type) {
        ViewInfo viewInfo = ComponentRule.WIDGET_TYPE.get(type);
        if (viewInfo == null) {
            return defaultViewHolder();
        }
        int viewType = viewInfo.getViewType();
        switch (viewType) {
            case 0:
                createViewFactory(mContext, mPresenter);
                return viewFactory.createViewComponent(viewInfo);
            case 1:
                createViewHolderFactory(mContext, mPresenter);
                return viewHolderFactory.createViewHolderComponent(viewInfo);
        }
        return defaultViewHolder();
    }

    @Override
    public void createViewFactory(Context context, BasePresenter presenter) {
        if (viewFactory == null) {
            viewFactory = new ViewComponentFactory(context, mPresenter, mParentRoot);
        }
    }

    @Override
    public void createViewHolderFactory(Context context, BasePresenter presenter) {
        if (viewHolderFactory == null) {
            viewHolderFactory = new ViewHolderComponentFactory(context, mParentRoot);
        }

    }

    private Component defaultViewHolder() {
        return null;
    }

}
