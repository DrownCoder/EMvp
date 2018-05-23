package com.study.xuan.emvp.component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IComponentBind<T> {
    void bind(T item);

    void unBind();

}
