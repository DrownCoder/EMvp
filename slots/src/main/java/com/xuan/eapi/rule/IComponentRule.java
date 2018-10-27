package com.xuan.eapi.rule;

import com.xuan.annotation.ViewInfo;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :ComponentRule规则的接口映射
 */

public interface IComponentRule {
    ViewInfo obtainViewInfo(int id);

    int obtainComponentId(Class<?> clazz);

    ViewInfo obtainAttachViewInfo(Class<?> clazz, int id);
}
