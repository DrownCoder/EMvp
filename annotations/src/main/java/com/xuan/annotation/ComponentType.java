package com.xuan.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :the description of this file
 */
@Documented()
// 表示是基于编译时注解的
@Retention(RetentionPolicy.CLASS)
// 表示可以作用于成员变量，类、接口
@Target(ElementType.TYPE)
public @interface ComponentType {
    enum Support{
        ViewHolder,View
    }
    int value() default 0;

    Support type() default Support.ViewHolder;
}
