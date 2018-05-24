package com.study.xuan.emvp.model;

import com.study.xuan.emvp.BasePresenter;
import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */
@BindType(ComponentId.USER_INFO_LAYOUT)
public class Product implements PostEvent,IUserInfo{
    public boolean isPostEvent = false;
    public BasePresenter presenter;
    public int imgUrl = R.drawable.ic_launcher_foreground;
    public String title = "商品名称";

    public Product() {
        this(false, null);
    }

    public Product(boolean isPostEvent, BasePresenter presenter) {
        this.isPostEvent = isPostEvent;
        this.presenter = presenter;
    }

    @Override
    public boolean postEvent() {
        return isPostEvent;
    }

    @Override
    public BasePresenter postPresenter() {
        return presenter;
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
