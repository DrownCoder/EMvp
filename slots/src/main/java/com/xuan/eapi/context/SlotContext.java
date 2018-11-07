package com.xuan.eapi.context;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xuan.eapi.adaptercorlib.MagicAdapter;
import com.xuan.eapi.factory.custom.CustomFactory;
import com.xuan.eapi.helper.binder.IMapAttach;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.factory.ComponentFactory;
import com.xuan.eapi.factory.IComponentFactory;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.lifecycle.GCAdapter;
import com.xuan.eapi.lifecycle.IGC;
import com.xuan.eapi.lifecycle.ILifeCycle;
import com.xuan.eapi.lifecycle.ILifeRegistor;
import com.xuan.eapi.lifecycle.LifeOwner;
import com.xuan.eapi.rule.IRuleRegister;
import com.xuan.eapi.rule.RuleRegister;
import com.xuan.eapi.viewmodel.IPresent;

import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :结耦后的全局代理
 */

public class SlotContext<T> implements ILogicManger, IContextService,
        ILifeRegistor, IComponentFactory {
    private Context context;
    private ToolKitBuilder<T> builder;
    private IModerBinder<T> moderBinder;
    private CustomFactory customFactory;
    private ILogicManger logicManger;

    private IMapAttach mapAttach;
    private IComponentFactory componentFactory;
    private IRuleRegister ruleRegister;

    private List<T> mData;
    private View.OnClickListener eventCenter;
    private LifeOwner lifeOwner;
    private RecyclerView rcyRoot;
    private RecyclerView.Adapter mAdapter;

    public SlotContext(Context context, List<T> data) {
        this(new ToolKitBuilder<>(context, data));
    }

    public SlotContext(ToolKitBuilder<T> builder) {
        this.builder = builder;
        context = builder.getContext();
        mData = builder.getData();
        moderBinder = builder.getModerBinder();
        logicManger = builder.getLogicManger();
        ruleRegister = builder.getRuleRegister();
        eventCenter = builder.getEventCenter();
        customFactory = builder.getComponentFactory();
        mapAttach = builder.getMapAttach();
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getParentRoot() {
        return rcyRoot;
    }

    public T getItem(int pos) {
        if (mData == null) {
            return null;
        }
        if (pos < 0 || pos >= mData.size()) {
            return null;
        }
        return mData.get(pos);
    }

    public void setData(List<T> data) {
        this.mData = data;
    }

    public void notifyDataSetChanged() {
        if (mAdapter == null) {
            return;
        }
        mAdapter.notifyDataSetChanged();
    }

    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public int getItemType(int pos) {
        return moderBinder.getItemType(pos, getItem(pos));
    }

    public boolean isAttaching() {
        if (ruleRegister != null) {
            return ruleRegister.obtainRules() != null && ruleRegister.obtainRules().size() > 0;
        }
        return false;
    }

    /**
     * 绑定Map
     */
    public void attachRule(Class<?> clazz) {
        if (ruleRegister == null) {
            ruleRegister = new RuleRegister();
        }
        ruleRegister.registerRule(clazz);
    }

    /**
     * 获取映射表
     */
    public IMapAttach getAttachMap() {
        return mapAttach;
    }

    /**
     * 注册逻辑
     */
    @Override
    public void registerLogic(IPresent logic) {
        logicManger.registerLogic(logic);
        if (ILifeCycle.class.isAssignableFrom(logic.getClass())) {
            pushLife((ILifeCycle) logic);
        }
    }

    @Override
    public Map<Class<?>, IPresent> obtainViewLogicPool() {
        return logicManger.obtainViewLogicPool();
    }

    @Override
    public IPresent obtainLogic(Class<?> clazz) {
        return logicManger.obtainLogic(clazz);
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
        if (builder != null && customFactory != null) {
            return customFactory.create(context, parent, type);
        }
        return null;
    }

}
