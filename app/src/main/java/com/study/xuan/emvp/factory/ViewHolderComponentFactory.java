package com.study.xuan.emvp.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.adapter.ComponentViewAdapter;
import com.study.xuan.emvp.adapter.ComponentViewHolderAdapter;
import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.vh.ImageViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public class ViewHolderComponentFactory implements IViewHolderComponentFactory {
    private Context mContext;
    private LayoutInflater mInflater;
    private View rootView;

    public ViewHolderComponentFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public Component createViewHolderComponent(ViewInfo viewInfo) {
        RecyclerView.ViewHolder viewholder = null;
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        rootView = mInflater.inflate(viewInfo.getLayoutId(), null);
        switch (viewInfo.getId()) {
            case ComponentId.IMAGE_VH:
                viewholder = new ImageViewHolder(rootView);
                break;
            default:
                viewholder = reflectCreate(viewInfo.getView());
                break;

        }
        return new ComponentViewHolderAdapter(viewholder);
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
