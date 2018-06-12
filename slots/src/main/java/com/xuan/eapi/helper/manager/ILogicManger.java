package com.xuan.eapi.helper.manager;

import android.util.SparseArray;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;

import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/6/11.
 * Description :处理逻辑个数相关
 */

public interface ILogicManger {
    public void registerLogic(BasePresenter presenter);

    public void registerModelLogic(int id, BasePresenter presenter);

    public void prepareLogic();

    public void prepareLogic(List<Integer> pIds);

    public Map<Class<?>, BasePresenter> obtainViewLogicPool();

    public SparseArray<BasePresenter> obtainModelLogicPool();

    public ReflectPresenterFactory obtainLogicFactory();
}
