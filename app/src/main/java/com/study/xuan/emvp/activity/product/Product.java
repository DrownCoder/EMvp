package com.study.xuan.emvp.activity.product;

import com.xuan.annotation.BindType;

import static com.study.xuan.emvp.activity.product.Product.PRODUCT;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :the description of this file
 */
@BindType(PRODUCT)
public class Product {
    public static final int PRODUCT = 0;
    public String pName;
    public String price;
}
