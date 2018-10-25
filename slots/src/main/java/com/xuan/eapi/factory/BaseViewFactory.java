package com.xuan.eapi.factory;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author : xuan.
 * Date : 2018/9/6.
 * Description :反射创建ViewRoot
 */

public class BaseViewFactory  {
    protected Context context;

    public BaseViewFactory(Context context) {
        this.context = context;
    }

    public View reflectCreateView(Class<?> clazz) {
        View view = null;
        try {
            Constructor c = clazz.getConstructor(Context.class);
            view = (View) c.newInstance(context);
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
