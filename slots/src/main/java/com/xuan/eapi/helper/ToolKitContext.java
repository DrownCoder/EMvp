package com.xuan.eapi.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.Slots;
import com.xuan.eapi.factory.presenter.IPresenterFactory;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;
import com.xuan.eapi.helper.manger.DefaultModelManager;
import com.xuan.eapi.helper.manger.IModelManager;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.component.ComponentFactory;
import com.xuan.eapi.factory.component.IComponentFactory;
import com.xuan.eapi.manager.IModerBinder;
import com.xuan.eapi.manager.ModelManager;

import java.util.HashMap;
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
    private IComponentFactory componentFactory;
    private IPresenterFactory presenterFactory;
    private ReflectPresenterFactory reflectPresenterFactory;
    private RecyclerView rcyRoot;
    //presenter冗余，拆分Presenter，一个Activity中可能存在多个Presenter实例
    private HashMap<Class<?>, BasePresenter> globalPresenter;
    private SparseArray<BasePresenter> presenterPool;

    public ToolKitContext(Context context, List<Object> data) {
        this(context, data, new ModelManager(context, data));
    }

    public ToolKitContext(Context context, List<Object> data, IModerBinder moderBinder) {
        this.context = context;
        this.data = data;
        this.moderBinder = moderBinder;
        this.modelManager = new DefaultModelManager(data);
        this.globalPresenter = new HashMap<>();
    }

    public void startPresenterEngine() {
        startPresenterEngine(null);
    }

    public void startPresenterEngine(List<Integer> pIds) {
        if (presenterPool == null) {
            presenterPool = new SparseArray<>();
        }
        if (presenterFactory != null) {
            List<BasePresenter> cusPresenters = presenterFactory.createPresenter();
            int pid;
            for (BasePresenter presenter : cusPresenters) {
                pid = Slots.getInstance().obtainPresenterRule().obtainPresenterId(presenter.getClass());
                presenterPool.put(pid, presenter);
            }
        }
        if (pIds == null || pIds.size() == 0) {
            return;
        }
        Class<?> presentClazz;
        for (int pid : pIds) {
            if (presenterPool.get(pid) != null) {
                continue;
            }
            if (reflectPresenterFactory == null) {
                reflectPresenterFactory = new ReflectPresenterFactory(context);
            }
            presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
            presenterPool.put(pid, reflectPresenterFactory.reflectCreate(presentClazz));
        }
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

    public BasePresenter getPresenter(Class<?> viewClass) {
        return globalPresenter.get(viewClass);
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
