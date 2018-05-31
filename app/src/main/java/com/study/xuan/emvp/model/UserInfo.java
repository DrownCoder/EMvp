package com.study.xuan.emvp.model;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.BindType;
import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.imodel.PostEvent;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */
@BindType(ComponentId.USER_INFO_LAYOUT)
public class UserInfo implements PostEvent,IUserInfo{
    public boolean isIntercept = false;
    public BasePresenter presenter;
    public int imgUrl;
    public String name = "用户名字";

    public UserInfo() {
        this(false, null);
    }

    public UserInfo(boolean isIntercept, BasePresenter presenter) {
        this.isIntercept = isIntercept;
        this.presenter = presenter;
    }

    @Override
    public boolean interceptEvent() {
        return isIntercept;
    }

    @Override
    public BasePresenter postPresenter() {
        return presenter;
    }

    @Override
    public boolean singlePresenter() {
        return true;
    }

    @Override
    public int getImgUrl() {
        return imgUrl;
    }

    @Override
    public String getText() {
        return name;
    }
}
