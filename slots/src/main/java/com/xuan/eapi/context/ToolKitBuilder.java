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
import com.xuan.eapi.viewmodel.IViewNotify;

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
    private ILogicBinder logicBinder;
    private ILogicManger logicManger;
    private IComponentFactory componentFactory;
    private View.OnClickListener eventCenter;
    private IViewNotify viewNotify;
    private List<T> mData;

    public ToolKitBuilder(Context context, List<T> data) {
        this.context = context;
        this.mData = data;
    }

    public ToolKitBuilder(Context context) {
        this.context = context;
    }

    public ToolKitBuilder setData(List<T> data) {
        if (modelManager != null) {
            modelManager.setData(data);
        }
        return this;
    }

    public ToolKitBuilder addAll(List<T> data) {
        if (modelManager != null) {
            modelManager.addAll(data);
        }
        return this;
    }

    public ToolKitBuilder setModerBinder(IModerBinder<T> moderBinder) {
        this.moderBinder = moderBinder;
        return this;
    }

    public ToolKitBuilder setModerManager(IModelManager<T> moderManager) {
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

    public IViewNotify getViewNotify() {
        return viewNotify;
    }

    public void setViewNotify(IViewNotify viewNotify) {
        this.viewNotify = viewNotify;
    }

    public Context getContext() {
        return context;
    }

    public IModerBinder<T> getModerBinder() {
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

    public SlotContext build() {
        if (moderBinder == null) {
            this.moderBinder = new DefaultModelBinder();
        }
        if (modelManager == null) {
            this.modelManager = new DefaultModelManager<>(mData);
        }
        if (logicManger == null) {
            this.logicManger = new LogicManager(context);
        }
        if (logicBinder == null) {
            this.logicBinder = new LogicBinder(logicManger);
        }
        return new SlotContext(this);
    }
}
