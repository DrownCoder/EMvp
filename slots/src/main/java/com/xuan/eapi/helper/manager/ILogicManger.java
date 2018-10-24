package com.xuan.eapi.helper.manager;

import com.xuan.eapi.logic.IPresent;

import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/6/11.
 * Description :处理逻辑个数相关
 */

public interface ILogicManger {
    void registerLogic(IPresent presenter);

    Map<Class<?>, IPresent> obtainViewLogicPool();

    IPresent obtainLogic(Class<?> clazz);
}
