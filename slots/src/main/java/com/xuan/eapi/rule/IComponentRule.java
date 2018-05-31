package com.xuan.eapi.rule;

import com.xuan.annotation.ViewInfo;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :ComponentRule规则的接口映射
 */

public interface IComponentRule {
    public ViewInfo obtainViewInfo(int id);

    public int obtainComponentId(Class<?> clazz);
}
