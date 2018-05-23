package com.study.xuan.emvp.factory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.ViewInfo;
import com.study.xuan.emvp.adapter.ComponentViewAdapter;
import com.study.xuan.emvp.component.Component;
import com.study.xuan.emvp.component.IComponentBind;
import com.study.xuan.emvp.component.widget.UserInfoLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :View转ViewHolder
 */

public class ViewComponentFactory implements IViewComponentFactory {
    private Context mContext;
    private ViewGroup mParentRoot;

    public ViewComponentFactory(Context context, ViewGroup parentRoot) {
        this.mContext = context;
        this.mParentRoot = parentRoot;
    }

    @Override
    public Component createViewComponent(ViewInfo viewInfo) {
        IComponentBind view = null;
        switch (viewInfo.getId()) {
            case ComponentId.USER_INFO_LAYOUT:
                view = new UserInfoLayout(mContext);
                break;
            default:
                view = reflectCreate(viewInfo.getView());
        }
        return new ComponentViewAdapter((View) view);
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
