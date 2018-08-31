package com.study.xuan.emvp.model;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.PresenterId;
import com.xuan.annotation.BindType;
import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.vm.ICarryLogic;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */
@BindType(ComponentId.USER_INFO_LAYOUT)
public class Product implements ICarryLogic, IUserInfo {
    public boolean isPostEvent = false;
    public BaseLogic presenter;
    public int imgUrl;
    public String title = "商品名称";

    public Product() {
        this(false);
    }

    public Product(boolean isPostEvent) {
        this.isPostEvent = isPostEvent;
    }

    @Override
    public BaseLogic postPresenter() {
        return presenter;
    }

    @Override
    public void injectPresenter(BaseLogic presenter) {
        this.presenter = presenter;
    }

    @Override
    public int presenterId() {
        return PresenterId.CART_PRESENTER;
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
