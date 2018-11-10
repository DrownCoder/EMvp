package com.xuan.eapi.context;

import android.content.Context;
import android.view.View;

import com.xuan.eapi.factory.custom.CustomFactory;
import com.xuan.eapi.helper.binder.DefaultModelBinder;
import com.xuan.eapi.helper.map.IMapAttach;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.helper.manager.LogicManager;
import com.xuan.eapi.helper.strategy.IMixStrategy;
import com.xuan.eapi.rule.IRuleRegister;
import com.xuan.eapi.rule.RuleRegister;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/12.
 * Description :工具类建造者
 */

public class ToolKitBuilder<T> {
    private WeakReference<Context> context;
    //可以拓展API
    //处理Model对应的ViewType
    private IModerBinder<T> moderBinder;
    //处理组件的创建工厂
    private CustomFactory componentFactory;
    //处理Mix混合打通类型的策略
    private IMixStrategy<T> mixStrategy;
    //全局的事件中心
    private View.OnClickListener eventCenter;
    //内部的API不可修改
    //逻辑池，逻辑管理类
    private ILogicManger logicManger;
    //映射表，处理Model和Type的映射关系
    private IMapAttach mapAttach;
    //映射表注册类，处理支持的多映射表
    private IRuleRegister ruleRegister;

    private List<T> mData;

    public ToolKitBuilder(Context context, List<T> data) {
        if (context == null) {
            throw new IllegalArgumentException("the context can't be null!!!");
        }
        this.context = new WeakReference<>(context);
        this.mData = data;
    }

    public ToolKitBuilder(Context context) {
        this.context = new WeakReference<>(context);
    }

    public ToolKitBuilder<T> setData(List<T> data) {
        mData = data;
        return this;
    }

    public List<T> getData() {
        return mData;
    }

    public ToolKitBuilder<T> setModerBinder(IModerBinder<T> moderBinder) {
        this.moderBinder = moderBinder;
        return this;
    }

    public IMapAttach getMapAttach() {
        return mapAttach == null ? dfMapAttach() : mapAttach;
    }

    public ToolKitBuilder<T> setEventCenter(View.OnClickListener onClickListener) {
        this.eventCenter = onClickListener;
        return this;
    }

    public WeakReference<Context> getContext() {
        return context;
    }

    public IModerBinder<T> getModerBinder() {
        return moderBinder == null ? dfModelBinder() : moderBinder;
    }

    public ILogicManger getLogicManger() {
        return logicManger == null ? dfLogicManager() : logicManger;
    }

    public ToolKitBuilder<T> setComponentFactory(CustomFactory componentFactory) {
        this.componentFactory = componentFactory;
        return this;
    }

    public CustomFactory getComponentFactory() {
        return componentFactory;
    }

    public View.OnClickListener getEventCenter() {
        return eventCenter;
    }

    public IRuleRegister getRuleRegister() {
        return ruleRegister;
    }

    public IMixStrategy<T> getMixStrategy() {
        return mixStrategy;
    }

    public ToolKitBuilder<T> setMixStrategy(IMixStrategy<T> mixStrategy) {
        this.mixStrategy = mixStrategy;
        return this;
    }

    /**
     * 绑定Map
     */
    public ToolKitBuilder<T> attachRule(Class<?> clazz) {
        if (ruleRegister == null) {
            ruleRegister = new RuleRegister();
        }
        ruleRegister.registerRule(clazz);
        return this;
    }

    public SlotContext<T> build() {
        dfModelBinder();
        dfLogicManager();
        return new SlotContext<>(this);
    }

    private IModerBinder<T> dfModelBinder() {
        if (moderBinder == null) {
            this.moderBinder = new DefaultModelBinder<>();
        }
        return moderBinder;
    }

    private ILogicManger dfLogicManager() {
        if (logicManger == null) {
            this.logicManger = new LogicManager();
        }
        return logicManger;
    }

    private IMapAttach dfMapAttach() {
        if (mapAttach == null) {
            this.mapAttach = new DefaultMapAttach();
        }
        return mapAttach;
    }


    /**
     * Author : xuan.
     * Date : 2018/10/29.
     * Description :返回type和componentId的映射关系，因为存在两种映射表
     * 1.全局映射表：componentId全局唯一，和VH一对一对应
     * 2.临时映射表：componentId的映射表和attachClass()返回的Class对应绑定，在attachClass()内的映射表内唯一
     */
    public class DefaultMapAttach implements IMapAttach {

        @Override
        public Class<?> attachClass(int type) {
            if (mixStrategy != null) {
                return mixStrategy.attachClass(type);
            }
            if (getRuleRegister() != null && getRuleRegister().obtainRules() != null &&
                    getRuleRegister().obtainRules().size() == 1) {
                return getRuleRegister().obtainRules().get(0);
            }
            return Object.class;
        }
    }
}
