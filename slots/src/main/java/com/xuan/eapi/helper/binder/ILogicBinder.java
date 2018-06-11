package com.xuan.eapi.helper.binder;

import com.xuan.eapi.BasePresenter;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description : View和对应逻辑
 */

public interface ILogicBinder {
    public BasePresenter bindViewLogic(Class<?> clazz);

    public BasePresenter bindModelLogic(int pid);
}
