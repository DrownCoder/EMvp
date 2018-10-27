package com.xuan.eapi.context;

import android.content.Context;
import android.view.View;

import com.xuan.eapi.factory.IComponentFactory;
import com.xuan.eapi.factory.IViewComponentFactory;
import com.xuan.eapi.helper.binder.DefaultModelBinder;
import com.xuan.eapi.helper.binder.IModerBinder;
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

public class ToolKitBuilder<T> {
    private Context context;
    private IModerBinder<T> moderBinder;
    private IModelManager<T> modelManager;
    private ILogicManger logicManger;
    private IComponentFactory componentFactory;
    private IViewComponentFactory viewComponentFactory;
    private View.OnClickListener eventCenter;
    private List<T> mData;
    private Class<?> attachClass;

    public ToolKitBuilder(Context context, List<T> data) {
        this.context = context;
        this.mData = data;
    }

    public ToolKitBuilder(Context context) {
        this.context = context;
    }

    public ToolKitBuilder setData(List<T> data) {
        mData = data;
        if (modelManager != null) {
            modelManager.setData(mData);
        }
        return this;
    }

    public ToolKitBuilder addAll(List<T> data) {
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

    public ToolKitBuilder setModerBinder(IModerBinder<T> moderBinder) {
        this.moderBinder = moderBinder;
        return this;
    }

    public ToolKitBuilder setModerManager(IModelManager<T> moderManager) {
        this.modelManager = moderManager;
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

    public ToolKitBuilder<T> attachClass(Class<?> clazz) {
        this.attachClass = clazz;
        return this;
    }

    public Class<?> getAttachClass() {
        if (attachClass == null) {
            attachClass = Object.class;
        }
        return attachClass;
    }

    public SlotContext build() {
        dfModelBinder();
        dfModelManager();
        dfLogicManager();
        return new SlotContext(this);
    }

    private IModelManager<T> dfModelManager() {
        if (modelManager == null) {
            this.modelManager = new DefaultModelManager<>(mData);
        }
        return modelManager;
    }

    private IModerBinder<T> dfModelBinder() {
        if (moderBinder == null) {
            this.moderBinder = new DefaultModelBinder();
        }
        return moderBinder;
    }

    private ILogicManger dfLogicManager() {
        if (logicManger == null) {
            this.logicManger = new LogicManager();
        }
        return logicManger;
    }
}
