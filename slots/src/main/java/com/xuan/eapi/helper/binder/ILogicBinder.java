package com.xuan.eapi.helper.binder;

import com.xuan.eapi.BaseLogic;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description : View和对应逻辑
 */

public interface ILogicBinder {
    public BaseLogic bindViewLogic(Class<?> clazz);

    public BaseLogic bindModelLogic(int pid);
}
