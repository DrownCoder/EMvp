package com.xuan.annotation;


/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :View类型信息
 */

public class ViewInfo {
    public static final int TYPE_VIEW = 0;
    public static final int TYPE_VIEWHOLDER = 1;
    //View类
    private Class<?> viewClass;
    //View类型
    private int viewType;
    //componentId
    private int id;
    //layoutId
    private int layoutId;

    public ViewInfo(int id, Class<?> viewclass, int viewtype) {
        this.id = id;
        this.viewClass = viewclass;
        this.viewType = viewtype;
    }

    public ViewInfo(int id, Class<?> viewclass, int layoutId, int viewtype) {
        this.id = id;
        this.viewClass = viewclass;
        this.layoutId = layoutId;
        this.viewType = viewtype;
    }

    public Class<?> getView() {
        return viewClass;
    }

    public int getViewType() {
        return viewType;
    }

    public int getId() {
        return id;
    }

    public int getLayoutId() {
        return layoutId;
    }
}
