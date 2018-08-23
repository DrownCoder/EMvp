package com.xuan.eapi.factory.component;

import android.content.Context;
import android.view.View;


import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.adapter.ComponentViewAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :View转ViewHolder
 */

public class ViewComponentFactory implements IViewComponentFactory, ReflectCreate<IComponentBind>, AdapterComponent {
    private Context mContext;

    public ViewComponentFactory(SlotContext slotContext) {
        this.mContext = slotContext.getContext();
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
            Constructor c = clazz.getConstructor(Context.class);
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
