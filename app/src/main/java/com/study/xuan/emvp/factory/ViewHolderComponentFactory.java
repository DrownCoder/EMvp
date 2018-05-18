package com.study.xuan.emvp.factory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.study.xuan.emvp.ViewInfo;
import com.study.xuan.emvp.component.Component;

import java.lang.reflect.Constructor;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public class ViewHolderComponentFactory implements IViewHolderComponentFactory {
    private Context mContext;
    public ViewHolderComponentFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public Component createViewHolderComponent(ViewInfo type) {

        return null;
    }

    @Override
    public RecyclerView.ViewHolder reflectCreate(Class<?> clazz) {
        RecyclerView.ViewHolder holder = null;
        try {
            Constructor constructor = clazz.getConstructor(View.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
