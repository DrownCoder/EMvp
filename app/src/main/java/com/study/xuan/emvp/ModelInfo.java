package com.study.xuan.emvp;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :数据信息
 */

public class ModelInfo {
    //映射关系，一对n，一对一，n对一
    public enum BindMap{
        OTN,OTO,NTO
    }
    //数据model的类
    private Class<?> modelClass;
    //绑定类型
    private BindMap bindType;
}
