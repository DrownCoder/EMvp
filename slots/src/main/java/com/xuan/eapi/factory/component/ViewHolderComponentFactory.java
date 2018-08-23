package com.xuan.eapi.factory.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.context.SlotContext;
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

public class ViewHolderComponentFactory implements IViewHolderComponentFactory,
        ReflectCreate<RecyclerView.ViewHolder>, AdapterComponent {
    private Context mContext;
    private LayoutInflater mInflater;
    private View rootView;
    private ViewGroup mParentRoot;

    public ViewHolderComponentFactory(SlotContext slotContext) {
        this.mContext = slotContext.getContext();
        this.mParentRoot = slotContext.getParentRoot();
    }

    @Override
    public Component adapterComponent(IComponentBind componentBind) {
        return new ComponentViewHolderAdapter(mContext, (RecyclerView.ViewHolder) componentBind);
    }

    @Override
    public IComponentBind createViewHolderComponent(ViewInfo viewInfo) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        rootView = mInflater.inflate(viewInfo.getLayoutId(), mParentRoot, false);
        return (IComponentBind) reflectCreate(viewInfo.getView());
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
