package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.xuan.eapi.BasePresenter;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */

public class SinglePresenter extends BasePresenter implements IUserInfoPresenter {
    public SinglePresenter(Context context) {
        super(context);
    }

    @Override
    public void onTextClick(Object o) {
        Toast.makeText(mContext, "这是配的另类逻辑（属于Model）", Toast.LENGTH_SHORT).show();
    }
}
