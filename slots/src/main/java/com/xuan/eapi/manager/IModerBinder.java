package com.xuan.eapi.manager;

import com.xuan.eapi.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :the description of this file
 */

public interface IModerBinder {
    int getItemType(int pos);

    IComponentBind createView(int type);
}
