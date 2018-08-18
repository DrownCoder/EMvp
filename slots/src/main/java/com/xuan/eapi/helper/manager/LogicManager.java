package com.xuan.eapi.helper.manager;

import android.content.Context;
import android.util.SparseArray;

import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.Slots;
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
    private Context context;
    //presenter冗余，拆分Presenter，一个Activity中可能存在多个Presenter实例
    private Map<Class<?>, BaseLogic> globalLogic;
    private SparseArray<BaseLogic> modelLogic;
    private ReflectPresenterFactory reflectPresenterFactory;

    public LogicManager(Context context) {
        this.context = context;
    }

    @Override
    public void registerLogic(BaseLogic presenter) {
        Class<?>[] inters = presenter.getClass().getInterfaces();
        for (Class clazz : inters) {
            obtainViewLogicPool().put(clazz, presenter);
        }
    }

    @Override
    public void registerModelLogic(int id, BaseLogic presenter) {
        obtainModelLogicPool().put(id, presenter);
    }

    @Override
    public void prepareLogic() {
        prepareLogic(null);
    }


    @Override
    public void prepareLogic(List<Integer> pIds) {
        modelLogic = obtainModelLogicPool();
        /*if (presenterFactory != null) {
            List<BaseLogic> cusPresenters = presenterFactory.createPresenter();
            int pid;
            for (BaseLogic presenter : cusPresenters) {
                pid = Slots.getInstance().obtainPresenterRule().obtainPresenterId(presenter.getClass());
                modelLogic.put(pid, presenter);
            }
        }*/
        if (pIds == null || pIds.size() == 0) {
            return;
        }
        Class<?> presentClazz;
        for (int pid : pIds) {
            if (modelLogic.get(pid) != null) {
                continue;
            }
            presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
            modelLogic.put(pid, obtainLogicFactory().reflectCreate(presentClazz));
        }
    }

    @Override
    public Map<Class<?>, BaseLogic> obtainViewLogicPool() {
        if (globalLogic == null) {
            globalLogic = new HashMap<>();
        }
        return globalLogic;
    }

    @Override
    public SparseArray<BaseLogic> obtainModelLogicPool() {
        if (modelLogic == null) {
            modelLogic = new SparseArray<>();
        }
        return modelLogic;
    }

    @Override
    public ReflectPresenterFactory obtainLogicFactory() {
        if (reflectPresenterFactory == null) {
            reflectPresenterFactory = new ReflectPresenterFactory(context);
        }
        return reflectPresenterFactory;
    }
}
