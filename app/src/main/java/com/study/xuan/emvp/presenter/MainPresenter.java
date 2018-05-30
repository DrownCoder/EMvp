package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.model.UserInfo;
import com.xuan.eapi.BasePresenter;


/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */

public class MainPresenter extends BasePresenter implements IUserInfoPresenter<UserInfo> {

    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(UserInfo userInfo) {
        Toast.makeText(mContext, "用户逻辑", Toast.LENGTH_SHORT).show();
    }
}
