package com.xuan.eapi.helper.strategy;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :混合模式的策略
 */
public interface IMixStrategy<T> {
    //通过type得到真正的映射表中的ComponentId
    int getComponentId(int type);
    //通过Type确定对应的映射表
    Class<?> attachClass(int type);
    //传入ViewHolder的Bind中的实体类
    Object getBindItem(int pos, T t);
}
