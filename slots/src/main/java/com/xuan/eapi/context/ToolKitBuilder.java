package com.xuan.eapi.context;

import android.content.Context;
import android.view.View;

import com.xuan.eapi.factory.component.IComponentFactory;
import com.xuan.eapi.helper.binder.DefaultModelBinder;
import com.xuan.eapi.helper.binder.ILogicBinder;
import com.xuan.eapi.helper.binder.IModerBinder;
import com.xuan.eapi.helper.binder.LogicBinder;
import com.xuan.eapi.helper.manager.DefaultModelManager;
import com.xuan.eapi.helper.manager.ILogicManger;
import com.xuan.eapi.helper.manager.IModelManager;
import com.xuan.eapi.helper.manager.LogicManager;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/12.
 * Description :工具类建造者
 */

public class ToolKitBuilder {
    private Context context;
    private IModerBinder moderBinder;
    private IModelManager modelManager;
    private ILogicBinder logicBinder;
    private ILogicManger logicManger;
    private IComponentFactory componentFactory;
    private View.OnClickListener eventCenter;

    public ToolKitBuilder(Context context, List<Object> data) {
        this.context = context;
        this.moderBinder = new DefaultModelBinder();
        this.modelManager = new DefaultModelManager(data);
        this.logicManger = new LogicManager(context);
        this.logicBinder = new LogicBinder(logicManger);
    }

    public static ToolKitBuilder init(Context context, List<Object> mData) {
        return new ToolKitBuilder(context, mData);
    }

    public ToolKitBuilder setModerBinder(IModerBinder moderBinder) {
        this.moderBinder = moderBinder;
        return this;
    }

    public ToolKitBuilder setModerManager(IModelManager moderManager) {
        this.modelManager = moderManager;
        return this;
    }

    public ToolKitBuilder setLogicBinder(ILogicBinder logicBinder) {
        this.logicBinder = logicBinder;
        return this;
    }

    public ToolKitBuilder setLogicManger(ILogicManger logicManger) {
        this.logicManger = logicManger;
        return this;
    }

    public ToolKitBuilder setComponentFactory(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        return this;
    }

    public ToolKitBuilder setEventCenter(View.OnClickListener onClickListener) {
        this.eventCenter = onClickListener;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public IModerBinder getModerBinder() {
        return moderBinder;
    }

    public IModelManager getModelManager() {
        return modelManager;
    }

    public ILogicBinder getLogicBinder() {
        return logicBinder;
    }

    public ILogicManger getLogicManger() {
        return logicManger;
    }

    public IComponentFactory getComponentFactory() {
        return componentFactory;
    }

    public View.OnClickListener getEventCenter() {
        return eventCenter;
    }

    public ToolKitContext build() {
        return new ToolKitContext(this);
    }
}
