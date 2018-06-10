package com.xuan.eapi.manager.binder;

import com.xuan.eapi.BasePresenter;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description : View和对应逻辑
 */

public interface ILogicBinder {
    public void registerLogic(BasePresenter presenter);

    public void prepareLogic();

    public void prepareLogic(List<Integer> pIds);

    public BasePresenter obtainPageLogic(Class<?> clazz);

    public BasePresenter obtainModelLogic(int pid);
}
