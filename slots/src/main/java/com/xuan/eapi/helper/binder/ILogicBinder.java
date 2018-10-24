package com.xuan.eapi.helper.binder;

import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.logic.IPresent;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description : View和对应逻辑
 */

public interface ILogicBinder {
    public IPresent bindViewLogic(Class<?> clazz);
}
