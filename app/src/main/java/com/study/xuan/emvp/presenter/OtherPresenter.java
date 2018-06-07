package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.hardware.usb.UsbRequest;
import android.widget.Toast;

import com.study.xuan.emvp.PresenterId;
import com.study.xuan.emvp.model.Product;
import com.study.xuan.emvp.model.UserInfo;
import com.xuan.annotation.RegisterLogic;
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
    public void onTextClick(Product userInfo) {
        Toast.makeText(mContext, "单品逻辑，进入单品页", Toast.LENGTH_SHORT).show();
    }
}
