package com.xuan.eapi.helper.binder;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :处理Model和Type、size的关系
 */

public class DefaultModelBinder<T> extends ModelBinder<T> {
    @Override
    protected int bindItemType(int pos, T obj) {
        return 0;
    }
}
