package com.xuan.eapi.context;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.manager.binder.ILogicBinder;
import com.xuan.eapi.manager.binder.LogicManager;
import com.xuan.eapi.manager.count.DefaultModelManager;
import com.xuan.eapi.manager.count.IModelManager;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.component.ComponentFactory;
import com.xuan.eapi.factory.component.IComponentFactory;
import com.xuan.eapi.manager.binder.IModerBinder;
import com.xuan.eapi.manager.binder.ModelBinderManager;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :工具包
 */

public class ToolKitContext implements ILogicBinder {
    private Context context;
    private IModerBinder moderBinder;
    private IModelManager modelManager;
    private ILogicBinder presenterBinder;
    private List<Object> data;
    private IComponentFactory componentFactory;
    private RecyclerView rcyRoot;

    public ToolKitContext(Context context, List<Object> data) {
        this(context, data, new ModelBinderManager(context, data));
    }

    public ToolKitContext(Context context, List<Object> data, IModerBinder moderBinder) {
        this.context = context;
        this.data = data;
        this.moderBinder = moderBinder;
        this.modelManager = new DefaultModelManager(data);
        this.presenterBinder = new LogicManager(this);
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

    @Override
    public void registerLogic(BasePresenter presenter) {
        presenterBinder.registerLogic(presenter);
    }
    @Override
    public void prepareLogic() {
        presenterBinder.prepareLogic();
    }
    @Override
    public void prepareLogic(List<Integer> pids) {
        presenterBinder.prepareLogic(pids);
    }
    @Override
    public BasePresenter obtainPageLogic(Class<?> viewClass) {
        return presenterBinder.obtainPageLogic(viewClass);
    }

    @Override
    public BasePresenter obtainModelLogic(int pid) {
        return presenterBinder.obtainModelLogic(pid);
    }

    public Component createComponent(int viewType, ViewGroup parent) {
        rcyRoot = (RecyclerView) parent;
        if (componentFactory == null) {
            componentFactory = new ComponentFactory(this);
        }
        return componentFactory.createViewHolder(viewType);
    }

    public IComponentBind createView(int viewType) {
        return moderBinder.createView(viewType);
    }
}
