package com.xuan.eapi.factory;

import android.content.Context;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.helper.Slots;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/9/6.
 * Description :the description of this file
 */

public abstract class MagicCommponentFactory implements IComponentFactory {
    @Override
    public Component createViewHolder(Context context, ViewGroup parent, int type) {
        IComponentBind component = null;
        ViewInfo viewInfo = Slots.getInstance().obtainRule().obtainViewInfo(type);
        if (!viewInfo.isAutoCreate()) {
            component = createComponent(viewInfo.getId());
        }
        return (Component) component;
    }

    public abstract IComponentBind createComponent(int viewId);
}
