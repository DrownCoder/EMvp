package com.xuan.eapi.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.helper.manger.DefaultModelManager;
import com.xuan.eapi.helper.manger.IModelManager;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.ComponentFactory;
import com.xuan.eapi.factory.IComponentFactory;
import com.xuan.eapi.manager.IModerBinder;
import com.xuan.eapi.manager.ModelManager;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :工具包
 */

public class ToolKitContext {
    private Context context;
    private IModerBinder moderBinder;
    private IModelManager modelManager;
    private List<Object> data;
    private IComponentFactory factory;
    private RecyclerView rcyRoot;
    private BasePresenter globalPresenter;

    public ToolKitContext(Context context, List<Object> data) {
        this(context, data, new ModelManager(context, data));
    }

    public ToolKitContext(Context context, List<Object> data, IModerBinder moderBinder) {
        this.context = context;
        this.data = data;
        this.moderBinder = moderBinder;
        this.modelManager = new DefaultModelManager(data);
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getParentRoot() {
        return rcyRoot;
    }

    public Object getItem(int pos) {
        return modelManager.getItem(pos);
    }

    public int getItemCount() {
        return modelManager.getItemCount();
    }

    public int getItemType(int pos) {
        return moderBinder.getItemType(pos);
    }

    public BasePresenter getPresenter() {
        return globalPresenter;
    }

    public Component createComponent(int viewType, ViewGroup parent) {
        rcyRoot = (RecyclerView) parent;
        if (factory == null) {
            factory = new ComponentFactory(this);
        }
        return factory.createViewHolder(viewType);
    }

    public IComponentBind createView(int viewType) {
        return moderBinder.createView(viewType);
    }
}
