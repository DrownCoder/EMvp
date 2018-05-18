package com.study.xuan.emvp.factory;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */

public interface ReflectCreate<T> {
    T reflectCreate(Class<?> clazz);
}
