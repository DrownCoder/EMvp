package com.xuan.eapi.helper.event;

/**
 * Author : xuan.
 * Date : 2018/12/8.
 * Description :the description of this file
 */
public interface EventDriver<T> {
    void onDoEvent(int eventID, T data);
}
