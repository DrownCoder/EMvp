package com.study.xuan.emvp.presenter;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :the description of this file
 */

public interface Contract {
    public interface ImagePresenter{
        void onImgClick();
    }

    public interface TwoImgPresenter{
        void onLeftClick();

        void onRightClick();
    }
}
