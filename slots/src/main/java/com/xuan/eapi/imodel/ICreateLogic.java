package com.xuan.eapi.imodel;


import com.xuan.eapi.BaseLogic;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :逻辑创建器，适合Model和Logic的一对一绑定
 */

public interface ICreateLogic {
    //处理该model的presenter
    BaseLogic postPresenter();

    //注入Presenter
    void injectPresenter(BaseLogic presenter);

    //要求注入的Presenter的pid
    int presenterId();
}
