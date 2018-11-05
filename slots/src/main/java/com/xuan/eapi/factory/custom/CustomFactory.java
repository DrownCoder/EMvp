package com.xuan.eapi.factory.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.eapi.adapter.ComponentViewAdapter;
import com.xuan.eapi.adapter.ComponentViewHolderAdapter;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/11/5.
 * Description :定制创建过程
 */
public abstract class CustomFactory {

    public Component create(Context context, ViewGroup parent, int type) {
        IComponentBind component;
        //自定义继承Component
        component = createComponent(context, parent, type);
        if (component == null) {
            //自定义View实现IComponentBind接口
            component = createView(context, parent, type);
        }
        if (component == null) {
            //原生ViewHolder实现IComponentBind接口
            component = createViewHolder(context, parent, type);
        } else {
            //自定义View转换成Component
            component = new ComponentViewAdapter(context, (View) component);
        }

        if (component != null) {
            //原生ViewHolder转换成Component
            component = new ComponentViewHolderAdapter(context, (RecyclerView.ViewHolder)
                    component);
        }
        return (Component) component;
    }

    protected IComponentBind createViewHolder(Context context, ViewGroup parent, int type) {
        return null;
    }

    protected IComponentBind createView(Context context, ViewGroup parent, int type) {
        return null;
    }

    protected IComponentBind createComponent(Context context, ViewGroup parent, int type) {
        return null;
    }
}
