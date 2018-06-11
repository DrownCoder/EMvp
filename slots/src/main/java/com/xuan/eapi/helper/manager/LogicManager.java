package com.xuan.eapi.helper.manager;

import android.util.SparseArray;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.Slots;
import com.xuan.eapi.context.ToolKitContext;
import com.xuan.eapi.factory.presenter.IPresenterFactory;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/6/11.
 * Description :the description of this file
 */

public class LogicManager implements ILogicManger {
    private ToolKitContext tookContext;
    //presenter冗余，拆分Presenter，一个Activity中可能存在多个Presenter实例
    private HashMap<Class, BasePresenter> globalLogic;
    private SparseArray<BasePresenter> modelLogic;
    private IPresenterFactory presenterFactory;
    private ReflectPresenterFactory reflectPresenterFactory;

    @Override
    public void registerLogic(BasePresenter presenter) {
        Class<?>[] inters = presenter.getClass().getInterfaces();
        for (Class clazz : inters) {
            globalLogic.put(clazz, presenter);
        }
    }

    @Override
    public void prepareLogic() {
        prepareLogic(null);
    }


    @Override
    public void prepareLogic(List<Integer> pIds) {
        if (modelLogic == null) {
            modelLogic = new SparseArray<>();
        }
        if (presenterFactory != null) {
            List<BasePresenter> cusPresenters = presenterFactory.createPresenter();
            int pid;
            for (BasePresenter presenter : cusPresenters) {
                pid = Slots.getInstance().obtainPresenterRule().obtainPresenterId(presenter.getClass());
                modelLogic.put(pid, presenter);
            }
        }
        if (pIds == null || pIds.size() == 0) {
            return;
        }
        Class<?> presentClazz;
        for (int pid : pIds) {
            if (modelLogic.get(pid) != null) {
                continue;
            }
            if (reflectPresenterFactory == null) {
                reflectPresenterFactory = new ReflectPresenterFactory(tookContext.getContext());
            }
            presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
            modelLogic.put(pid, reflectPresenterFactory.reflectCreate(presentClazz));
        }
    }

    @Override
    public Map obtainViewLogicPool() {
        return globalLogic;
    }

    @Override
    public SparseArray obtainModelLogicPool() {
        if (modelLogic == null) {
            modelLogic = new SparseArray<>();
        }
        return modelLogic;
    }

    @Override
    public ReflectPresenterFactory obtainLogicFactory() {
        if (reflectPresenterFactory == null) {
            reflectPresenterFactory = new ReflectPresenterFactory(tookContext.getContext());
        }
        return reflectPresenterFactory;
    }
}
