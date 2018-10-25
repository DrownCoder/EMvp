package com.xuan.eapi.factory;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :反射创建
 */

public interface ReflectCreate<T> {
    T reflectCreate(Class<?> clazz);
}
