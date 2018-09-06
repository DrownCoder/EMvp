package com.xuan.eapi.factory.component;

import android.content.Context;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.Slots;
import com.xuan.eapi.component.Component;

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
