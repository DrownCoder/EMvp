package com.xuan.eapi.helper.manager;

import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.factory.presenter.ReflectPresenterFactory;

import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/6/11.
 * Description :处理逻辑个数相关
 */

public interface ILogicManger {
    public void registerLogic(BaseLogic presenter);

    public Map<Class<?>, BaseLogic> obtainViewLogicPool();

    public ReflectPresenterFactory obtainLogicFactory();
}
