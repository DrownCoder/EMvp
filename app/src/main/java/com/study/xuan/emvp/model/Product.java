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
public class Product implements PostEvent,IUserInfo{
    public boolean isPostEvent = false;
    public BasePresenter presenter;
    public int imgUrl;
    public String title = "商品名称";

    public Product() {
        this(false, null);
    }

    public Product(boolean isPostEvent, BasePresenter presenter) {
        this.isPostEvent = isPostEvent;
        this.presenter = presenter;
    }

    @Override
    public boolean interceptEvent() {
        return isPostEvent;
    }

    @Override
    public BasePresenter postPresenter() {
        return presenter;
    }

    @Override
    public boolean singlePresenter() {
        return false;
    }

    @Override
    public int getImgUrl() {
        return imgUrl;
    }

    @Override
    public String getText() {
        return title;
    }
}
