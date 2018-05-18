package com.study.xuan.emvp.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.study.xuan.emvp.ComponentRule;
import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.component.Component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :组件工厂
 */

public class ComponentFactory implements IComponentFactory {
    private Context Context;

    public ComponentFactory(Context context) {
        this.Context = context;
    }

    private IViewComponentFactory viewFactory;
    private IViewHolderComponentFactory viewHolderFactory;
    @Override
    public Component createViewHolder(int type) {
        ViewInfo viewInfo = ComponentRule.WIDGET_TYPE.get(type);
        if (viewInfo == null) {
            return defaultViewHolder();
        }
        Class viewType = viewInfo.getViewType();
        if (viewType == View.class) {
            createViewFactory(Context);
            return viewFactory.createViewComponent(viewInfo);
        } else if (viewType == RecyclerView.ViewHolder.class) {
            createViewHolderFactory(Context);
            return viewHolderFactory.createViewHolderComponent(viewInfo);
        }
        return defaultViewHolder();
    }

    @Override
    public void createViewFactory(Context context) {
        if (viewFactory == null) {
            viewFactory = new ViewComponentFactory(context);
        }
    }

    @Override
    public void createViewHolderFactory(Context context) {
        if (viewHolderFactory == null) {
            viewHolderFactory = new ViewHolderComponentFactory(context);
        }

    }

    private Component defaultViewHolder() {
        return null;
    }

}
