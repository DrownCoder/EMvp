package com.study.xuan.emvp.factory;

import android.content.Context;
import android.view.View;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.adapter.ViewAdapter;
import com.study.xuan.emvp.vh.Component;
import com.study.xuan.emvp.widget.IWidget;
import com.study.xuan.emvp.widget.UserInfoLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :View转ViewHolder
 */

public class ViewComponentFactory implements IViewComponentFactory {
    private Context mContext;

    public ViewComponentFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public Component createViewComponent(ViewInfo viewInfo) {
        IWidget view = null;
        switch (viewInfo.getId()) {
            case ComponentId.USER_INFO_LAYOUT:
                view = new UserInfoLayout(mContext);
                break;
            default:
                view = reflectCreate(viewInfo.getView());
        }
        return new ViewAdapter((View) view);
    }

    @Override
    public IWidget reflectCreate(Class<?> clazz) {
        IWidget view = null;
        try {
            Constructor c = clazz.getConstructor(clazz, Context.class);
             view = (IWidget) c.newInstance(mContext);
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
