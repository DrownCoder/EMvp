package com.xuan.eapi.factory;

import android.content.Context;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.context.SlotContext;

/**
 * Author : xuan.
 * Date : 2018/9/26.
 * Description :the description of this file
 */

public class CustomComponentFactory implements IViewComponentFactory,IComponentFactory {
    private SlotContext context;

    public CustomComponentFactory(SlotContext context) {
        this.context = context;
    }

    @Override
    public Component createViewHolder(Context context, ViewGroup parent, int type) {
        return null;
    }

    @Override
    public IComponentBind createViewComponent(ViewInfo type) {
        return null;
    }
}
