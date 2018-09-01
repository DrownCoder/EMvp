package com.xuan.eapi.helper.binder;

import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.helper.manager.ILogicManger;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :区分逻辑对应的View,用于处理View和逻辑的对应关系
 */

public class LogicBinder implements ILogicBinder {
    private ILogicManger logicManger;
    public LogicBinder(ILogicManger logicManger) {
        this.logicManger = logicManger;
    }

    /**
     * View的逻辑都交给逻辑P处理
     * @param clazz View的对应Class类型
     * @return 返回用于处理该View类型的逻辑
     */
    @Override
    public BaseLogic bindViewLogic(Class<?> clazz) {
        return logicManger.obtainViewLogicPool().get(clazz);
    }
}
