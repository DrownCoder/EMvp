package com.xuan.eapi.context;

import android.content.Context;
import android.view.View;

import com.xuan.eapi.factory.IComponentFactory;
import com.xuan.eapi.factory.IViewComponentFactory;
import com.xuan.eapi.helper.binder.DefaultMapAttach;
import com.xuan.eapi.helper.binder.DefaultModelBinder;
import com.xuan.eapi.helper.binder.IMapAttach;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.helper.manager.DefaultModelManager;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.helper.manager.IModelManager;
import com.xuan.eapi.helper.manager.LogicManager;
import com.xuan.eapi.rule.IRuleRegister;
import com.xuan.eapi.rule.RuleRegister;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/12.
 * Description :工具类建造者
 */

public class ToolKitBuilder<T> {
    private Context context;
    private IModerBinder<T> moderBinder;
    private IModelManager<T> modelManager;
    private ILogicManger logicManger;
    private IComponentFactory componentFactory;
    private IMapAttach mapAttach;
    private IRuleRegister ruleRegister;
    private IViewComponentFactory viewComponentFactory;
    private View.OnClickListener eventCenter;
    private List<T> mData;

    public ToolKitBuilder(Context context, List<T> data) {
        this.context = context;
        this.mData = data;
    }

    public ToolKitBuilder(Context context) {
        this.context = context;
    }

    public ToolKitBuilder<T> setData(List<T> data) {
        mData = data;
        if (modelManager != null) {
            modelManager.setData(mData);
        }
        return this;
    }

    public ToolKitBuilder<T> addAll(List<T> data) {
        if (mData != null) {
            mData.addAll(data);
        } else {
            mData = data;
        }
        if (modelManager != null) {
            modelManager.addAll(mData);
        }
        return this;
    }

    public ToolKitBuilder<T> setModerBinder(IModerBinder<T> moderBinder) {
        this.moderBinder = moderBinder;
        return this;
    }

    public ToolKitBuilder<T> setModerManager(IModelManager<T> moderManager) {
        this.modelManager = moderManager;
        return this;
    }

    public ToolKitBuilder<T> setLogicManger(ILogicManger logicManger) {
        this.logicManger = logicManger;
        return this;
    }

    public IMapAttach getMapAttach() {
        return mapAttach == null ? dfMapAttach() : mapAttach;
    }

    public ToolKitBuilder<T> setMapAttach(IMapAttach mapAttach) {
        this.mapAttach = mapAttach;
        return this;
    }

    public ToolKitBuilder<T> setComponentFactory(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        return this;
    }

    public ToolKitBuilder<T> setEventCenter(View.OnClickListener onClickListener) {
        this.eventCenter = onClickListener;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public IModerBinder<T> getModerBinder() {
        return moderBinder == null ? dfModelBinder() : moderBinder;
    }

    public IModelManager<T> getModelManager() {
        return modelManager == null ? dfModelManager() : modelManager;
    }

    public ILogicManger getLogicManger() {
        return logicManger == null ? dfLogicManager() : logicManger;
    }

    public IComponentFactory getComponentFactory() {
        return componentFactory;
    }

    public IViewComponentFactory getViewComponentFactory() {
        return viewComponentFactory;
    }

    public void setViewComponentFactory(IViewComponentFactory viewComponentFactory) {
        this.viewComponentFactory = viewComponentFactory;
    }

    public View.OnClickListener getEventCenter() {
        return eventCenter;
    }

    public IRuleRegister getRuleRegister() {
        return ruleRegister;
    }

    public ToolKitBuilder<T> setRuleRegister(IRuleRegister ruleRegister) {
        this.ruleRegister = ruleRegister;
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
        dfModelManager();
        dfLogicManager();
        return new SlotContext<>(this);
    }

    private IModelManager<T> dfModelManager() {
        if (modelManager == null) {
            this.modelManager = new DefaultModelManager<>(mData);
        }
        return modelManager;
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
            if (getRuleRegister() != null && getRuleRegister().obtainRules() != null &&
                    getRuleRegister().obtainRules().size() == 1) {
                return getRuleRegister().obtainRules().get(0);
            }
            return Object.class;
        }

        @Override
        public int getComponentType(int type) {
            return type;
        }
    }
}
