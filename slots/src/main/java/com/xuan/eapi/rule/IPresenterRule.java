package com.xuan.eapi.rule;

/**
 * Author : xuan.
 * Date : 2018/6/1.
 * Description :逻辑映射规则
 */

public interface IPresenterRule {
    int obtainPresenterId(Class<?> clazz);

    Class<?> obtainPresenter(int pid);
}
