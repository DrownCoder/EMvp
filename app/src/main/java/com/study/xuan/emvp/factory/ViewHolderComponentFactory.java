package com.study.xuan.emvp.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.ViewInfo;
import com.study.xuan.emvp.adapter.ComponentViewHolderAdapter;
import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.vh.ImageViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the RecyclerView.ViewHolder Factory
 */

public class ViewHolderComponentFactory implements IViewHolderComponentFactory, ReflectCreate<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private View rootView;
    private ViewGroup mParentRoot;

    public ViewHolderComponentFactory(Context context, ViewGroup parentRoot) {
        this.mContext = context;
        this.mParentRoot = parentRoot;
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
            viewHolder = selfCreateViewHolder(viewInfo);
        }
        return new ComponentViewHolderAdapter(viewHolder);
    }

    private RecyclerView.ViewHolder selfCreateViewHolder(ViewInfo viewInfo) {
        switch (viewInfo.getId()) {
            case ComponentId.IMAGE_VH:
                return new ImageViewHolder(rootView);
            default:
                return null;
        }
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
