package com.study.xuan.emvp.activity.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xuan.eapi.helper.event.AdapterEventDriver;

/**
 * Author : xuan.
 * Date : 2018/12/8.
 * Description :the description of this file
 */
public class ProductEventDriver extends AdapterEventDriver<Product> {


    public ProductEventDriver(Context context, RecyclerView.Adapter adapter) {
        super(context, adapter);
    }

    @Override
    public void onDoEvent(int eventID, Product product) {
        Toast.makeText(mContext, "点击单品" + product.pName, Toast.LENGTH_SHORT).show();
    }
}
