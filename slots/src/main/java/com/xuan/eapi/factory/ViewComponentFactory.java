package com.xuan.eapi.factory;

import android.content.Context;
import android.view.View;


import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.adapter.ComponentViewAdapter;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.helper.SlotsMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :View转ViewHolder
 */

public class ViewComponentFactory implements IViewComponentFactory, ReflectCreate<IComponentBind>, AdapterComponent {
    private Context mContext;

    public ViewComponentFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public IComponentBind createViewComponent(ViewInfo viewInfo) {
        return reflectCreate(viewInfo.getView());
    }

    /**
     * 反射调构造函数
     */
    @Override
    public IComponentBind reflectCreate(Class<?> clazz) {
        IComponentBind view = null;
        try {
            Constructor c = SlotsMap.getInstance().obtainConstructor(clazz);
            if (c == null) {
                c = clazz.getConstructor(Context.class);
                SlotsMap.getInstance().cacheConstructor(clazz, c);
            }
            view = (IComponentBind) c.newInstance(mContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public Component adapterComponent(IComponentBind componentBind) {
        return new ComponentViewAdapter(mContext,(View) componentBind);
    }
}
