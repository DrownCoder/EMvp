package com.xuan.eapi.helper.binder;

import android.util.SparseArray;

import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.Slots;
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
    public BasePresenter bindViewLogic(Class<?> clazz) {
        return logicManger.obtainViewLogicPool().get(clazz);
    }

    /**
     * 当多个Model对应同一个View类型，但是某一个Model的逻辑与其他Model的逻辑处理不同，
     * 这时就不能走bindViewLogic返回的逻辑类型，而是需要走Model自己携带逻辑
     * @param pid 逻辑Id，当某个Model是特殊逻辑时，对应与特殊的逻辑Id
     * @return 返回这个Model所对应的逻辑
     */
    @Override
    public BasePresenter bindModelLogic(int pid) {
        SparseArray<BasePresenter> logicPool = logicManger.obtainModelLogicPool();
        BasePresenter presenter;
        if (logicPool != null) {
            presenter = logicPool.get(pid);
            if (presenter != null) {
                return presenter;
            }
        }

        Class<?> presentClazz;
        presentClazz = Slots.getInstance().obtainPresenterRule().obtainPresenter(pid);
        presenter = logicManger.obtainLogicFactory().reflectCreate(presentClazz);
        logicPool.put(pid, presenter);
        return presenter;
    }
}
