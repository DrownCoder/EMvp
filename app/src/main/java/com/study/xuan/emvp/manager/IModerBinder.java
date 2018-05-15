package com.study.xuan.emvp.manager;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :the description of this file
 */

public interface IModerBinder {
    int getCount();

    int getItemType(int pos);

    Object getItem(int pos);
}
