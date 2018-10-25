package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.PresenterId;
import com.study.xuan.emvp.model.Product;
import com.xuan.annotation.RegisterLogic;
import com.xuan.eapi.viewmodel.BaseLogic;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */
@RegisterLogic(PresenterId.CART_PRESENTER)
public class CartLogic extends BaseLogic implements IUserInfoPresenter<Product> {
    public CartLogic(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(Product o) {
        Toast.makeText(mContext, "购物车逻辑", Toast.LENGTH_SHORT).show();
    }
}
