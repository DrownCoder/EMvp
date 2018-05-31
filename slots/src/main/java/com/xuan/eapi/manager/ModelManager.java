package com.xuan.eapi.manager;


import android.content.Context;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.Slots;
import com.xuan.eapi.rule.Const;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.imodel.HandlerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :处理Model和Type、size的关系
 */

public class ModelManager implements IModerBinder {
    private List<Object> mData;
    private Context mContext;

    public ModelManager(Context context, List<Object> data) {
        this.mContext = context;
        if (data == null) {
            this.mData = new ArrayList<>();
        } else {
            this.mData = data;
        }
    }

    @Override
    public int getItemType(int pos) {
        Object item = mData.get(pos);
        if (HandlerType.class.isAssignableFrom(item.getClass())) {
            //多样式
            return ((HandlerType) item).handlerType();
        } else {
            //一对一
            return Slots.getInstance().obtainRule().obtainComponentId(item.getClass());
        }
    }

    @Override
    public IComponentBind createView(int type) {
        ViewInfo viewInfo = Slots.getInstance().obtainRule().obtainViewInfo(type);
        if (!viewInfo.isAutoCreate()) {
            createComponent(mContext, viewInfo.getId());
        }
        return null;
    }

    public IComponentBind createComponent(Context context, int viewId){
        return null;
    }
}
