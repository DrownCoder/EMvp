package com.xuan.eapi.factory.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.helper.ToolKitContext;
import com.xuan.eapi.component.Component;
import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.adapter.ComponentViewHolderAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the RecyclerView.ViewHolder Factory
 */

public class ViewHolderComponentFactory implements IViewHolderComponentFactory, ReflectCreate<RecyclerView.ViewHolder> {
    private ToolKitContext tookContext;
    private Context mContext;
    private LayoutInflater mInflater;
    private View rootView;
    private ViewGroup mParentRoot;

    public ViewHolderComponentFactory(ToolKitContext toolKitContext) {
        this.tookContext = toolKitContext;
        this.mContext = toolKitContext.getContext();
        this.mParentRoot = toolKitContext.getParentRoot();
    }

    @Override
    public Component createViewHolderComponent(ViewInfo viewInfo) {
        RecyclerView.ViewHolder viewHolder;
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        rootView = mInflater.inflate(viewInfo.getLayoutId(), mParentRoot, false);
        if (viewInfo.isAutoCreate()) {
            viewHolder = reflectCreate(viewInfo.getView());
        } else {
            viewHolder = (RecyclerView.ViewHolder) selfCreateViewHolder(viewInfo);
        }
        return new ComponentViewHolderAdapter(viewHolder);
    }

    private IComponentBind selfCreateViewHolder(ViewInfo viewInfo) {
        return tookContext.createView(viewInfo.getId());
    }

    @Override
    public RecyclerView.ViewHolder reflectCreate(Class<?> clazz) {
        RecyclerView.ViewHolder holder = null;
        try {
            Constructor c = clazz.getConstructor(View.class);
            holder = (RecyclerView.ViewHolder) c.newInstance(rootView);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return holder;
    }
}
