package com.study.xuan.emvp.activity.product;

import com.xuan.annotation.BindType;
import com.xuan.eapi.helper.event.EventDriver;
import com.xuan.eapi.helper.event.IOutputEvent;

import static com.study.xuan.emvp.activity.product.Product.PRODUCT;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :the description of this file
 */
@BindType(PRODUCT)
public class Product implements IOutputEvent<Product> {
    public static final int PRODUCT = 0;
    public String pName;
    public String price;

    private EventDriver ProductEvent;

    @Override
    public void injectDriver(EventDriver driver) {
        this.ProductEvent = driver;
    }

    @Override
    public void onDoEvent(int eventID, Product data) {
        this.ProductEvent.onDoEvent(eventID, data);
    }
}
