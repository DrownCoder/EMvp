package com.study.xuan.emvp.model;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */
@BindType(ComponentId.USER_INFO_LAYOUT)
public class Product implements IUserInfo {
    public boolean isPostEvent = false;
    public int imgUrl;
    public String title = "商品名称";

    public Product() {
        this(false);
    }

    public Product(boolean isPostEvent) {
        this.isPostEvent = isPostEvent;
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
