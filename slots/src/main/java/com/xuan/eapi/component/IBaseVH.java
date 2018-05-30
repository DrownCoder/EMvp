package com.xuan.eapi.component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IBaseVH<T> {
    void onBind(int pos,T item);

    void onUnBind();

    void onCreate(T item);
}
