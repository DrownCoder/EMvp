package com.xuan.eapi.helper.binder;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :数据对应的组件样式
 */

public interface IModerBinder<T> {
    int getItemType(int pos, T t);
}
