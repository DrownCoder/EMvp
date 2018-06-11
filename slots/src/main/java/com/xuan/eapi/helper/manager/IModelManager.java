package com.xuan.eapi.helper.manager;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :数据处理类，处理数据个数，加header，加footer
 */

public interface IModelManager {
    int getItemCount();

    Object getItem(int pos);
}
