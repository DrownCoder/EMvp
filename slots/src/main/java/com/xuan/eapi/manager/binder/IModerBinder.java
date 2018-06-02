package com.xuan.eapi.manager.binder;

import com.xuan.eapi.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :数据对应的组件样式
 */

public interface IModerBinder {
    int getItemType(int pos);

    IComponentBind createView(int type);
}
