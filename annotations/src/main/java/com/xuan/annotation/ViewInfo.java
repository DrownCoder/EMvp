package com.xuan.annotation;


/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :View类型信息
 */

public class ViewInfo {
    public static final int LAYOUT_NONE = -1;
    public static final int TYPE_VIEW = 0;
    public static final int TYPE_VIEWHOLDER = 1;
    //View类
    private Class<?> viewClass;
    //View类型 0代表自定义View，1代表ViewHolder
    private int viewType;
    //componentId
    private int id;
    //layoutId
    private int layoutId;
    //是否自动创建
    private boolean autoCreate;
    //view的Presenter接口
    private Class<?> presenterClass;

    public ViewInfo(int id, Class<?> viewClass, int viewType) {
        this(id, viewClass, viewType, true);
    }

    public ViewInfo(int id, Class<?> viewClazz, int layoutId, int viewType) {
        this(id, viewClazz, layoutId, viewType, true);
    }

    public ViewInfo(int id, Class<?> viewClazz, int viewType, boolean autoCreate) {
        this(id, viewClazz, LAYOUT_NONE, viewType, autoCreate);
    }

    public ViewInfo(int id, Class<?> viewClazz, int layoutId, int viewType, boolean autoCreate) {
        this.id = id;
        this.viewClass = viewClazz;
        this.layoutId = layoutId;
        this.viewType = viewType;
        this.autoCreate = autoCreate;
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

    public boolean isAutoCreate() {
        return autoCreate;
    }

    public Class<?> getPresenter() {
        return presenterClass;
    }
}
