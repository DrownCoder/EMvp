package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.study.xuan.emvp.PresenterId;
import com.study.xuan.emvp.model.Product;
import com.xuan.annotation.RegisterLogic;
import com.xuan.eapi.BasePresenter;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */
@RegisterLogic(PresenterId.COMMUNITY_PRESENTER)
public class CommunityPresenter extends BasePresenter implements IUserInfoPresenter<Product> {
    public CommunityPresenter(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(Product o) {
        Toast.makeText(mContext, "社区逻辑，进入评论详情", Toast.LENGTH_SHORT).show();
    }
}
