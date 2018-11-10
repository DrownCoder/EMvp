package com.xuan.eapi.helper.binder;



import com.xuan.eapi.helper.SlotsMap;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :处理Model和Type、size的关系
 */

public abstract class ModelBinder<T> implements IModerBinder<T> {
    public int DF_BINDER_TYPE = Integer.MIN_VALUE;
    @Override
    public final int getItemType(int pos, T obj) {
        int type;
        type = bindItemType(pos, obj);
        if (type == DF_BINDER_TYPE) {
            if (HandlerType.class.isAssignableFrom(obj.getClass())) {
                //多样式
                return ((HandlerType) obj).handlerType();
            } else {
                //一对一
                return SlotsMap.getInstance().obtainRule().obtainComponentId(obj.getClass());
            }
        }
        return type;
    }
    protected abstract int bindItemType(int pos, T obj);
}
