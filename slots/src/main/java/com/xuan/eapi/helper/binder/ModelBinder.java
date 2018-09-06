package com.xuan.eapi.helper.binder;



import com.xuan.eapi.Slots;
import com.xuan.eapi.viewmodel.HandlerType;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :处理Model和Type、size的关系
 */

public abstract class ModelBinder implements IModerBinder {

    @Override
    public int getItemType(int pos, Object obj) {
        if (HandlerType.class.isAssignableFrom(obj.getClass())) {
            //多样式
            return ((HandlerType) obj).handlerType();
        } else {
            //一对一
            return Slots.getInstance().obtainRule().obtainComponentId(obj.getClass());
        }
    }
}
