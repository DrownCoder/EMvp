package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.model.Product;
import com.xuan.eapi.BasePresenter;

/**
 * Author : xuan.
 * Date : 2018/5/24.
 * Description :the description of this file
 */

public class OtherPresenter extends BasePresenter implements IUserInfoPresenter<Product> {
    public OtherPresenter(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(Product product) {
        Toast.makeText(mContext, "这是商品逻辑", Toast.LENGTH_SHORT).show();
    }
}
