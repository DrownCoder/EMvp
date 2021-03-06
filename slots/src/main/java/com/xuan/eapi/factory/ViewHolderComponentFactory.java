package com.xuan.eapi.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.eapi.component.Component;
import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.adapter.ComponentViewHolderAdapter;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.helper.SlotsMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the RecyclerView.ViewHolder Factory
 */

public class ViewHolderComponentFactory extends BaseViewFactory implements
        IViewHolderComponentFactory,
        ReflectCreate<RecyclerView.ViewHolder>, AdapterComponent {
    private LayoutInflater mInflater;
    private View rootView;
    private ViewGroup mParentRoot;

    public ViewHolderComponentFactory(Context context, ViewGroup root) {
        super(context);
        this.mParentRoot = root;
    }

    @Override
    public Component adapterComponent(IComponentBind componentBind) {
        return new ComponentViewHolderAdapter(context, (RecyclerView.ViewHolder) componentBind);
    }

    @Override
    public IComponentBind createViewHolderComponent(ViewInfo viewInfo) {
        if (viewInfo.getParentClass() != null &&
                viewInfo.getParentClass() != Object.class) {
            rootView = reflectCreateView(viewInfo.getParentClass());
        } else {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(context);
            }
            rootView = mInflater.inflate(viewInfo.getLayoutId(), mParentRoot, false);
        }
        return (IComponentBind) reflectCreate(viewInfo.getView());
    }

    @Override
    public RecyclerView.ViewHolder reflectCreate(Class<?> clazz) {
        RecyclerView.ViewHolder holder = null;
        try {
            Constructor c = SlotsMap.getInstance().obtainConstructor(clazz);
            if (c == null) {
                c = clazz.getConstructor(View.class);
                SlotsMap.getInstance().cacheConstructor(clazz, c);
            }
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
