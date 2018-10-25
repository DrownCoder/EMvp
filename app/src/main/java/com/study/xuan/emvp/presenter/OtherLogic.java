package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.model.UserInfo;
import com.xuan.eapi.viewmodel.BaseLogic;
import com.xuan.eapi.viewmodel.IPresent;

/**
 * Author : xuan.
 * Date : 2018/5/24.
 * Description :the description of this file
 */
public class OtherLogic extends BaseLogic implements IUserInfoPresenter<UserInfo>,IPresent {
    public OtherLogic(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(UserInfo userInfo) {
        Toast.makeText(mContext, "个人信息逻辑，进入个人主页", Toast.LENGTH_SHORT).show();
    }
}
