package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.BasePresenter;
import com.study.xuan.emvp.model.UserInfo;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */

public class MainPreseneter extends BasePresenter implements IUserInfoPresenter<UserInfo> {

    public MainPreseneter(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(UserInfo userInfo) {
        Toast.makeText(mContext, "MainPresenter的逻辑", Toast.LENGTH_SHORT).show();
    }
}
