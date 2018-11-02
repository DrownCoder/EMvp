package com.xuan.eapi.factory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.helper.SlotsMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/9/4.
 * Description :默认继承Component的创建工厂
 */

public class DFComponentFactory extends BaseViewFactory implements IDFComponentFactory,
        ReflectCreate<Component>, AdapterComponent {
    private LayoutInflater mInflater;
    private ViewGroup mParentRoot;
    private View rootView;

    public DFComponentFactory(Context context, ViewGroup parent) {
        super(context);
        this.mParentRoot = parent;
    }

    @Override
    public Component reflectCreate(Class<?> clazz) {
        Component component = null;
        try {
            Constructor c = SlotsMap.getInstance().obtainConstructor(clazz);
            if (c == null) {
                c = clazz.getConstructor(Context.class, View.class);
                SlotsMap.getInstance().cacheConstructor(clazz, c);
            }
            component = (Component) c.newInstance(context, rootView);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return component;
    }

    @Override
    public Component createViewHolder(ViewInfo viewInfo) {
        if (viewInfo.getParentClass() != null &&
                viewInfo.getParentClass() != Object.class) {
            rootView = reflectCreateView(viewInfo.getParentClass());
        } else {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(context);
            }
            rootView = mInflater.inflate(viewInfo.getLayoutId(), mParentRoot, false);
        }
        return reflectCreate(viewInfo.getView());
    }

    @Override
    public Component adapterComponent(IComponentBind componentBind) {
        return (Component) componentBind;
    }
}
