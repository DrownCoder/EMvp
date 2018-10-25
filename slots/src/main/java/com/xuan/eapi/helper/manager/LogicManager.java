package com.xuan.eapi.helper.manager;

import com.xuan.eapi.viewmodel.IPresent;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/6/11.
 * Description :the description of this file
 */

public class LogicManager implements ILogicManger {
    //Logic冗余，拆分Logic，一个Activity中可能存在多个Logic实例
    private Map<Class<?>, IPresent> globalLogic;

    public LogicManager() {
    }

    @Override
    public void registerLogic(IPresent presenter) {
        Class<?>[] inters = presenter.getClass().getInterfaces();
        for (Class clazz : inters) {
            obtainViewLogicPool().put(clazz, presenter);
        }
    }

    @Override
    public Map<Class<?>, IPresent> obtainViewLogicPool() {
        if (globalLogic == null) {
            globalLogic = new HashMap<>();
        }
        return globalLogic;
    }

    @Override
    public IPresent obtainLogic(Class<?> clazz) {
        return obtainViewLogicPool().get(clazz);
    }
}
