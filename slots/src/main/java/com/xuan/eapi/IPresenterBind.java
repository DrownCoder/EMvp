package com.xuan.eapi;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :注入Presenter
 */

public interface IPresenterBind<T> {
    void injectPresenter(T t);
}
