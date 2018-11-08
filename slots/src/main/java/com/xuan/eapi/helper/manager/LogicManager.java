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
    private Map<Class<?>, IPresent> commonLogic;

    public LogicManager() {
    }

    @Override
    public void registerLogic(IPresent presenter) {
        obtainViewLogicPool().put(presenter.getClass(), presenter);
        //todo 没必要以接口作为key，后期可以考虑删除，如果注册ViewHolder以接口注册，则需要作为key,但是太少了
        Class<?>[] inters = presenter.getClass().getInterfaces();
        if (inters != null) {
            for (Class clazz : inters) {
                obtainViewLogicPool().put(clazz, presenter);
            }
        }
    }

    @Override
    public Map<Class<?>, IPresent> obtainViewLogicPool() {
        if (commonLogic == null) {
            commonLogic = new HashMap<>();
        }
        return commonLogic;
    }

    @Override
    public IPresent obtainLogic(Class<?> clazz) {
        return obtainViewLogicPool().get(clazz);
    }
}
