package com.xuan.eapi.helper.event;

/**
 * Author : xuan.
 * Date : 2018/12/8.
 * Description :the description of this file
 */
public interface IOutputEvent<T> extends EventDriver<T> {
    void injectDriver(EventDriver<T> driver);
}
