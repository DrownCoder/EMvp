package com.xuan.eapi.lifecycle;

/**
 * Author : xuan.
 * Date : 2018/8/23.
 * Description :注册生命周期
 */

public interface ILifeRegistor {
    void pushLife(ILifeCycle lifeCycle);
}
