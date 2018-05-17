package com.study.xuan.emvp;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :View类型信息
 */

public class ViewInfo {
    //View类
    private Class<?> viewClass;
    private int id;
    //View类型
    private Class<?> viewType;

    public ViewInfo(int id, Class<?> viewclass, Class<?> viewtype) {
        this.id = id;
        this.viewClass = viewclass;
        this.viewType = viewtype;
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
}
