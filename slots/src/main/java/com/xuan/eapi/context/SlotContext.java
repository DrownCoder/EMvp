package com.xuan.eapi.context;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.adapter.MagicAdapter;
import com.xuan.eapi.factory.component.IViewComponentFactory;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;
import com.xuan.eapi.helper.binder.ILogicBinder;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.helper.manager.IModelManager;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.component.ComponentFactory;
import com.xuan.eapi.factory.component.IComponentFactory;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.lifecycle.GCAdapter;
import com.xuan.eapi.lifecycle.IGC;
import com.xuan.eapi.lifecycle.ILifeCycle;
import com.xuan.eapi.lifecycle.ILifeRegistor;
import com.xuan.eapi.lifecycle.LifeOwner;
import com.xuan.eapi.viewmodel.IViewNotify;

import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :结耦后的全局代理
 */

public class SlotContext<T> implements ILogicBinder, ILogicManger, IContextService,
        ILifeRegistor, IModelManager<T>, IComponentFactory, IViewComponentFactory {
    private Context context;
    private ToolKitBuilder builder;
    private IModerBinder<T> moderBinder;
    private IModelManager<T> modelManager;
    private ILogicBinder logicBinder;
    private ILogicManger logicManger;
    private IComponentFactory componentFactory;

    private View.OnClickListener eventCenter;
    private LifeOwner lifeOwner;
    private IViewNotify viewNotify;
    private RecyclerView rcyRoot;
    private RecyclerView.Adapter mAdapter;

    public SlotContext(Context context, List<T> data) {
        this(new ToolKitBuilder<>(context, data));
    }

    public SlotContext(ToolKitBuilder builder) {
        this.builder = builder;
        context = builder.getContext();
        moderBinder = builder.getModerBinder();
        modelManager = builder.getModelManager();
        logicBinder = builder.getLogicBinder();
        logicManger = builder.getLogicManger();
        eventCenter = builder.getEventCenter();
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getParentRoot() {
        return rcyRoot;
    }

    public T getItem(int pos) {
        return modelManager.getItem(pos);
    }

    @Override
    public void addAll(List<T> data) {
        modelManager.addAll(data);
    }

    @Override
    public void setData(List<T> data) {
        modelManager.setData(data);
    }

    public int getItemCount() {
        return modelManager.getItemCount();
    }

    public int getItemType(int pos) {
        return moderBinder.getItemType(pos, modelManager.getItem(pos));
    }

    /**
     * 注册逻辑
     */
    @Override
    public void registerLogic(BaseLogic logic) {
        logicManger.registerLogic(logic);
        pushLife(logic);
    }

    @Override
    public Map<Class<?>, BaseLogic> obtainViewLogicPool() {
        return logicManger.obtainViewLogicPool();
    }

    @Override
    public ReflectPresenterFactory obtainLogicFactory() {
        return logicManger.obtainLogicFactory();
    }

    @Override
    public BaseLogic bindViewLogic(Class<?> viewClass) {
        return logicBinder.bindViewLogic(viewClass);
    }

    public Component createComponent(int viewType, ViewGroup parent) {
        rcyRoot = (RecyclerView) parent;
        if (componentFactory == null) {
            componentFactory = new ComponentFactory(this);
        }
        return componentFactory.createViewHolder(context, parent, viewType);
    }

    @Override
    public View.OnClickListener obtainEventCenter() {
        return eventCenter;
    }

    public void bind(RecyclerView rcy) {
        this.rcyRoot = rcy;
        mAdapter = new MagicAdapter(this);
        rcyRoot.setAdapter(mAdapter);
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    /**
     * 监听全部的生命周期
     */
    public void pushLife(ILifeCycle lifeCycle) {
        if (lifeOwner == null) {
            lifeOwner = LifeOwner.init(context);
        }
        lifeOwner.pushLife(lifeCycle);
    }

    /**
     * 监听Destroy
     */
    public void pushGC(IGC gc) {
        if (lifeOwner == null) {
            lifeOwner = LifeOwner.init(context);
        }
        pushLife(new GCAdapter(gc));
    }

    @Override
    public Component createViewHolder(Context context, ViewGroup parent, int type) {
        if (builder != null && builder.getComponentFactory() != null) {
            return builder.getComponentFactory().createViewHolder(context, parent, type);
        }
        return null;
    }

    @Override
    public IComponentBind createViewComponent(ViewInfo type) {
        if (builder != null && builder.getViewComponentFactory() != null) {
            return builder.getViewComponentFactory().createViewComponent(type);
        }
        return null;
    }
}
