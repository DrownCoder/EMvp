
package com.xuan.eapi.component;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :楼层需要实现的接口
 */

public interface IComponentBind<T> {
    void onBind(int pos, T item);

    void onUnBind();

}