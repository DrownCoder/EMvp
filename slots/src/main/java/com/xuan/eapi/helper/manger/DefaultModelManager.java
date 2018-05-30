package com.xuan.eapi.helper.manger;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/30.
 * Description :默认数据，没header和footer
 */

public class DefaultModelManager implements IModelManager {
    private List<Object> data;

    public DefaultModelManager(List<Object> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public Object getItem(int pos) {
        if (data == null || pos < 0 || pos >= data.size()) {
            return null;
        }
        return data.get(pos);
    }
}
