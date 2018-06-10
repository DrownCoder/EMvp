package com.xuan.eapi.manager.binder;

import android.util.SparseArray;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.Slots;
import com.xuan.eapi.context.ToolKitContext;
import com.xuan.eapi.factory.presenter.IPresenterFactory;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :区分逻辑对应的View
 */

public class LogicManager implements ILogicBinder {
    private ToolKitContext tookContext;
    //presenter冗余，拆分Presenter，一个Activity中可能存在多个Presenter实例
    private HashMap<Class,BasePresenter> globalPresenter;
    private SparseArray<BasePresenter> presenterPool;
    private IPresenterFactory presenterFactory;
    private ReflectPresenterFactory reflectPresenterFactory;


    public LogicManager(ToolKitContext tookContext) {
        this.tookContext = tookContext;
        this.globalPresenter = new HashMap<>();
    }

    @Override
    public void registerLogic(BasePresenter presenter) {
        Class<?>[] inters = presenter.getClass().getInterfaces();
        for (Class clazz : inters) {
            globalPresenter.put(clazz, presenter);
        }
    }

    @Override
    public void prepareLogic() {
        prepareLogic(null);
    }

    @Override
    public void prepareLogic(List<Integer> pIds) {
        if (presenterPool == null) {
            presenterPool = new SparseArray<>();
        }
        if (presenterFactory != null) {
            List<BasePresenter> cusPresenters = presenterFactory.createPresenter();
            int pid;
            for (BasePresenter presenter : cusPresenters) {
                pid = Slots.getInstance().obtainPresenterRule().obtainPresenterId(presenter.getClass());
                presenterPool.put(pid, presenter);
            }
        }
        if (pIds == null || pIds.size() == 0) {
            return;
        }
        Class<?> presentClazz;
        for (int pid : pIds) {
            if (presenterPool.get(pid) != null) {
                continue;
            }
            if (reflectPresenterFactory == null) {
                reflectPresenterFactory = new ReflectPresenterFactory(tookContext.getContext());
            }
            presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
            presenterPool.put(pid, reflectPresenterFactory.reflectCreate(presentClazz));
        }
    }

    @Override
    public BasePresenter obtainPageLogic(Class<?> clazz) {
        return globalPresenter.get(clazz);
    }

    @Override
    public BasePresenter obtainModelLogic(int pid) {
        BasePresenter presenter;
        if (presenterPool != null) {
            presenter = presenterPool.get(pid);
            if (presenter != null) {
                return presenter;
            }
        }
        presenterPool = new SparseArray<>();
        if (reflectPresenterFactory == null) {
            reflectPresenterFactory = new ReflectPresenterFactory(tookContext.getContext());
        }
        Class<?> presentClazz;
        presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
        presenter = reflectPresenterFactory.reflectCreate(presentClazz);
        presenterPool.put(pid, presenter);
        return presenter;
    }
}
