package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.PresenterId;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.xuan.annotation.RegisterLogic;
import com.xuan.eapi.viewmodel.BaseLogic;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */
@RegisterLogic(PresenterId.COMMUNITY_PRESENTER)
public class CommunityLogic extends BaseLogic implements IUserInfoPresenter<CommonModel> {
    public CommunityLogic(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(CommonModel o) {
        Toast.makeText(mContext, "社区逻辑，进入评论详情", Toast.LENGTH_SHORT).show();
    }
}
