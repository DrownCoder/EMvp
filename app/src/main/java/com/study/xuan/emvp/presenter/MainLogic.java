package com.study.xuan.emvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xuan.eapi.viewmodel.BaseLogic;


/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */

public class MainLogic extends BaseLogic
        implements Contract.ImagePresenter, Contract.TwoImgPresenter {

    public MainLogic(Context context) {
        super(context);
    }

    @Override
    public void onImgClick() {
        Toast.makeText(mContext, "一行一图，一张图片的点击逻辑，这是Activity的默认逻辑1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLeftClick() {
        Toast.makeText(mContext, "一行两图，左边图片点击，这是Activity的默认逻辑1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightClick() {
        Toast.makeText(mContext, "一行两图，右图片点击，这是Activity的默认逻辑1", Toast.LENGTH_SHORT).show();
    }
}
