package com.xuan.eapi.rule;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/10/30.
 * Description :注册映射规则
 */
public interface IRuleRegister {
    void registerRule(Class<?> clazz);

    List<Class<?>> obtainRules();
}
