package com.xuan.eapi.factory.presenter;

import com.xuan.eapi.BasePresenter;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/1.
 * Description :Presenter工厂
 */

public interface ILogicFactory {
    List<BasePresenter> createLogic();
}
