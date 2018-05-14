package com.study.xuan.emvp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/5/5.
 * Description :input the description of this file
 */

public class BasePresenterFactory implements PresenterFactory {
    private Object owener;
    Map<Object, List<IBasePresenter>> pool;

    @Override
    public IBasePresenter create(Object presenter) {
        if (pool == null) {
            pool = new HashMap();
        }
        List cachePresenters = pool.get(owener);
        if (cachePresenters != null) {
        }

        return null;
    }
}
