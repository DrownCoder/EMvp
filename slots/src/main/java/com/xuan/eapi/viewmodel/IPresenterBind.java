package com.xuan.eapi.viewmodel;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :注入Presenter，实现类这个接口的ViewHolder,并且使用类@ILogic注解，就可以注入逻辑类实例
 */

public interface IPresenterBind<T> {
    void injectPresenter(T t);
}
