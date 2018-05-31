package com.xuan.eapi.imodel;


import com.xuan.eapi.BasePresenter;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */

public interface PostEvent {
    //是否拦截逻辑
    boolean interceptEvent();
    //处理该model的presenter
    BasePresenter postPresenter();
    //model和presenter的关系一对一
    boolean singlePresenter();
}
