package com.study.xuan.emvp.presenter;

import com.study.xuan.emvp.model.UserInfo;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */

public interface IUserInfoPresenter<T extends UserInfo> {
    void onTextClick(T t);
}
