package com.xuan.eapi.context;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.adapter.DefaultEAdapter;
import com.xuan.eapi.adapter.EAdapter;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;
import com.xuan.eapi.helper.binder.DefaultModelBinder;
import com.xuan.eapi.helper.binder.ILogicBinder;
import com.xuan.eapi.helper.binder.LogicBinder;
import com.xuan.eapi.helper.manager.DefaultModelManager;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.helper.manager.IModelManager;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.component.ComponentFactory;
import com.xuan.eapi.factory.component.IComponentFactory;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.helper.manager.LogicManager;

import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :工具包
 */

public class ToolKitContext implements ILogicBinder, ILogicManger {
    private Context context;
    private IModerBinder moderBinder;
    private IModelManager modelManager;
    private ILogicBinder logicBinder;
    private ILogicManger logicManger;
    private List<Object> data;
    private IComponentFactory componentFactory;
    private RecyclerView rcyRoot;
    private EAdapter adapter;

    public ToolKitContext(Context context, List<Object> data) {
        this(context, data, new DefaultModelBinder());
    }

    public ToolKitContext(Context context, List<Object> data, IModerBinder moderBinder) {
        this.context = context;
        this.data = data;
        this.moderBinder = moderBinder;
        this.modelManager = new DefaultModelManager(data);
        this.logicManger = new LogicManager();
        this.logicBinder = new LogicBinder(logicManger);
        this.adapter = new DefaultEAdapter(this);
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getParentRoot() {
        return rcyRoot;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public Object getItem(int pos) {
        return modelManager.getItem(pos);
    }

    public int getItemCount() {
        return modelManager.getItemCount();
    }

    public int getItemType(int pos) {
        return moderBinder.getItemType(pos, modelManager.getItem(pos));
    }

    @Override
    public void registerLogic(BasePresenter presenter) {
        logicManger.registerLogic(presenter);
    }

    @Override
    public void prepareLogic() {
        logicManger.prepareLogic();
    }

    @Override
    public void prepareLogic(List<Integer> pids) {
        logicManger.prepareLogic(pids);
    }

    @Override
    public Map obtainViewLogicPool() {
        return logicManger.obtainViewLogicPool();
    }

    @Override
    public SparseArray obtainModelLogicPool() {
        return logicManger.obtainModelLogicPool();
    }

    @Override
    public ReflectPresenterFactory obtainLogicFactory() {
        return logicManger.obtainLogicFactory();
    }

    @Override
    public BasePresenter bindViewLogic(Class<?> viewClass) {
        return logicBinder.bindViewLogic(viewClass);
    }

    @Override
    public BasePresenter bindModelLogic(int pid) {
        return logicBinder.bindModelLogic(pid);
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
