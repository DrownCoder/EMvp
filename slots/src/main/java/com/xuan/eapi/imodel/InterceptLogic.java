package com.xuan.eapi.imodel;


import com.xuan.eapi.BaseLogic;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :逻辑拦截器，适合Model和Logic的一对一绑定
 */

public interface InterceptLogic {
    //是否拦截逻辑
    boolean interceptEvent();

    //处理该model的presenter
    BaseLogic postPresenter();

    //注入Presenter
    void injectPresenter(BaseLogic presenter);

    int presenterId();

    //model和presenter的关系一对一
    boolean singlePresenter();
}
