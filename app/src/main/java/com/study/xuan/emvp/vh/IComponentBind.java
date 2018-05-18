package com.study.xuan.emvp.vh;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface IComponentBind<T> {
    void onBind(int pos, T t);

    void onUnBind();
}
