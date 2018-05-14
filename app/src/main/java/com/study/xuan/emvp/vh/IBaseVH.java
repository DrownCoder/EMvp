package com.study.xuan.emvp.vh;

import com.study.xuan.emvp.model.Floor;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IBaseVH<T extends Floor> {
    void onBind(int pos,T item);

    void onUnBind();

    void onCreate(T item);
}
