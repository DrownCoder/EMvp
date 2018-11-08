package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.study.xuan.emvp.activity.common.CommonModel;
import com.study.xuan.emvp.activity.common.userinfo.IUserInfoPresenter;
import com.xuan.eapi.lifecycle.ILifeCycle;
import com.xuan.eapi.viewmodel.BaseLogic;
import com.xuan.eapi.viewmodel.IPresent;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */
public class CommunityLogic extends BaseLogic implements IUserInfoPresenter<CommonModel>,IPresent,ILifeCycle {
    public CommunityLogic(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(CommonModel o) {
        Toast.makeText(mContext, "社区逻辑，进入评论详情", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Log.i("xxxxxxxx", "onDestroy");
    }
}
