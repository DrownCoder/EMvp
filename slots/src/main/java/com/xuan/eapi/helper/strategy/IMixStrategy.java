package com.xuan.eapi.helper.strategy;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :混合模式的策略
 */
public interface IMixStrategy<T> {
    int getComponentId(int type);

    Class<?> attachClass(int type);

    Object getBindItem(int pos, T t);
}
