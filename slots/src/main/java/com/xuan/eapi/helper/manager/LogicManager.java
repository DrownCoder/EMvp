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
    //Logic冗余，拆分Logic，一个Activity中可能存在多个Logic实例
    private Map<Class<?>, BaseLogic> globalLogic;
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
    public Map<Class<?>, BaseLogic> obtainViewLogicPool() {
        if (globalLogic == null) {
            globalLogic = new HashMap<>();
        }
        return globalLogic;
    }

    @Override
    public ReflectPresenterFactory obtainLogicFactory() {
        if (reflectPresenterFactory == null) {
            reflectPresenterFactory = new ReflectPresenterFactory(context);
        }
        return reflectPresenterFactory;
    }
}
