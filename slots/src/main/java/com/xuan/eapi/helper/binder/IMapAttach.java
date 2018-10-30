package com.xuan.eapi.helper.binder;

/**
 * Author : xuan.
 * Date : 2018/10/29.
 * Description :组件映射表绑定
 */
public interface IMapAttach {
    Class<?> attachClass(int type);

    int getComponentType(int type);
}
