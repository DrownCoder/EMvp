package com.study.xuan.emvp;

import android.support.v7.widget.RecyclerView;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :View类型信息
 */

public class ViewInfo {
    //View类
    private Class<?> viewClass;
    //View类型
    private Class<?> viewType;
    //componentId
    private int id;
    //layoutId
    private int layoutId;

    public ViewInfo(int id, Class<?> viewclass, Class<?> viewtype) {
        this.id = id;
        this.viewClass = viewclass;
        this.viewType = viewtype;
    }

    public ViewInfo(int id, Class<?> viewclass, int layoutId) {
        this.id = id;
        this.viewClass = viewclass;
        this.layoutId = layoutId;
        this.viewType = RecyclerView.ViewHolder.class;
    }

    public Class<?> getView() {
        return viewClass;
    }

    public Class<?> getViewType() {
        return viewType;
    }

    public int getId() {
        return id;
    }

    public int getLayoutId() {
        return layoutId;
    }
}
