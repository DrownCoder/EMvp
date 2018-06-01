package com.xuan.eapi.factory.component;

import android.content.Context;
import android.view.View;


import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.IPresenterBind;
import com.xuan.eapi.helper.ToolKitContext;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.adapter.ComponentViewAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :View转ViewHolder
 */

public class ViewComponentFactory implements IViewComponentFactory, ReflectCreate<IComponentBind> {
    private ToolKitContext tContext;
    private Context mContext;

    public ViewComponentFactory(ToolKitContext toolKitContext) {
        this.tContext = toolKitContext;
        this.mContext = toolKitContext.getContext();
    }

    @Override
    public Component createViewComponent(ViewInfo viewInfo) {
        IComponentBind view = null;
        if (viewInfo.isAutoCreate()) {
            view = reflectCreate(viewInfo.getView());
        } else {
            view = selfCreateView(viewInfo);
        }

        //组件mvp,则需要实现IPresenterBind接口
        if (IPresenterBind.class.isAssignableFrom(view.getClass())) {
            IPresenterBind presenterBind = (IPresenterBind) view;
            presenterBind.setPresenter(tContext.getPresenter(viewInfo.getPresenter()));
        }
        return new ComponentViewAdapter((View) view);
    }

    private IComponentBind selfCreateView(ViewInfo viewInfo) {
        return tContext.createView(viewInfo.getId());
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
}
