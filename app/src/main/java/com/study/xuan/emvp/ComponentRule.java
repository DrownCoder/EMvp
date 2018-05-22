package com.study.xuan.emvp;

import android.util.SparseArray;
import android.view.View;

import com.study.xuan.emvp.component.vh.ImageViewHolder;
import com.study.xuan.emvp.model.SingleImg;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.component.widget.UserInfoLayout;
import com.xuan.annotation.ViewInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */

public class ComponentRule {
    public static final SparseArray<ViewInfo> WIDGET_TYPE;
    public static final Map<Class<?>, Integer> MODEL_TYPE;


    static {
        WIDGET_TYPE = new SparseArray<>();
        MODEL_TYPE = new HashMap<>();
        putWidget(ComponentId.USER_INFO_LAYOUT, new ViewInfo(ComponentId.USER_INFO_LAYOUT, UserInfoLayout.class, ViewInfo.TYPE_VIEW));
        putWidget(ComponentId.IMAGE_VH, new ViewInfo(ComponentId.IMAGE_VH, ImageViewHolder.class, R.layout.single_img));
        putModel(UserInfo.class, ComponentId.USER_INFO_LAYOUT);
        putModel(SingleImg.class, ComponentId.IMAGE_VH);
    }

    private static void putWidget(int id, ViewInfo classz) {
        WIDGET_TYPE.put(id, classz);
    }

    private static void putModel(Class<?> classz, int id) {
        MODEL_TYPE.put(classz, id);
    }

}
