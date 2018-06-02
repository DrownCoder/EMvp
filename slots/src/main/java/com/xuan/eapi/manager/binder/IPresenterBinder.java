package com.xuan.eapi.manager.binder;

import com.xuan.eapi.BasePresenter;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description : View和对应逻辑
 */

public interface IPresenterBinder {
    public void registerPresenter(BasePresenter presenter);

    public void startPresenterEngine();

    public void startPresenterEngine(List<Integer> pIds);

    public BasePresenter obtainPresenter(Class<?> clazz);
}
