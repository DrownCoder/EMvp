package com.xuan.eapi.helper.manager;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :默认数据，没header和footer
 */

public abstract class DefaultModelManager<T> implements IModelManager<T> {
    private List<T> mData;

    public DefaultModelManager(List<T> data) {
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public T getItem(int pos) {
        if (mData == null || pos < 0 || pos >= mData.size()) {
            return null;
        }
        return mData.get(pos);
    }

    @Override
    public void addAll(List<T> data) {
        if (mData == null) {
            setData(data);
        }
        mData.addAll(data);
    }

    @Override
    public void setData(List<T> data) {
        mData = data;
    }
}
