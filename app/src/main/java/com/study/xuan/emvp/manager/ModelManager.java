package com.study.xuan.emvp.manager;

import com.study.xuan.emvp.ComponentRule;
import com.study.xuan.emvp.Const;
import com.study.xuan.emvp.model.HandlerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :处理Model和Type、size的关系
 */

public class ModelManager implements IModerBinder{
    private List<Object> mData;

    public ModelManager(List<Object> data) {
        if (data == null) {
            this.mData = new ArrayList<>();
        }else{
            this.mData = data;
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public int getItemType(int pos) {
        Object item = mData.get(pos);
        if (HandlerType.class.isAssignableFrom(item.getClass())) {
            //多样式
            return ((HandlerType) item).handlerType();
        }else{
            //一对一
            Integer type = ComponentRule.MODEL_TYPE.get(item.getClass());
            if (type != null) {
                return type;
            }else {
                return Const.UNFIND_TYPE;
            }
        }
    }

    @Override
    public Object getItem(int pos) {
        return mData.get(pos);
    }
}
