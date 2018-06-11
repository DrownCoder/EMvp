package com.xuan.eapi.helper.binder;

import android.util.SparseArray;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.Slots;
import com.xuan.eapi.context.ToolKitContext;
import com.xuan.eapi.factory.presenter.IPresenterFactory;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;
import com.xuan.eapi.helper.manager.ILogicManger;

import java.util.HashMap;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :区分逻辑对应的View
 */

public class LogicBinder implements ILogicBinder {
    private ILogicManger logicManger;
    public LogicBinder(ILogicManger logicManger) {
        this.logicManger = logicManger;
    }

    @Override
    public BasePresenter bindViewLogic(Class<?> clazz) {
        return (BasePresenter) logicManger.obtainViewLogicPool().get(clazz);
    }

    @Override
    public BasePresenter bindModelLogic(int pid) {
        SparseArray logicPool = logicManger.obtainModelLogicPool();
        BasePresenter presenter;
        if (logicPool != null) {
            presenter = (BasePresenter) logicPool.get(pid);
            if (presenter != null) {
                return presenter;
            }
        }

        Class<?> presentClazz;
        presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
        presenter = logicManger.obtainLogicFactory().reflectCreate(presentClazz);
        logicPool.put(pid, presenter);
        return presenter;
    }
}
