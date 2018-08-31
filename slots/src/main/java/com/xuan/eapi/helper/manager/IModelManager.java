package com.xuan.eapi.helper.manager;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :数据处理类，处理数据个数，加header，加footer
 */

public interface IModelManager<T> {
    int getItemCount();

    T getItem(int pos);

    void addAll(List<T> data);

    void setData(List<T> data);
}
