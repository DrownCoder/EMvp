package com.xuan.eapi.helper.binder;

/**
 * Author : xuan.
 * Date : 2018/10/29.
 * Description :返回type和componentId的映射关系，因为存在两种映射表
 * 1.全局映射表：componentId全局唯一，和VH一对一对应
 * 2.临时映射表：componentId的映射表和attachClass()返回的Class对应绑定，在attachClass()内的映射表内唯一
 */
public class DefaultMapAttach implements IMapAttach {
    private Class<?> attachClass;

    public DefaultMapAttach(Class<?> attachClass) {
        this.attachClass = attachClass;
    }

    @Override
    public Class<?> attachClass() {
        return attachClass;
    }

    @Override
    public int getComponentType(int type) {
        return type;
    }
}
