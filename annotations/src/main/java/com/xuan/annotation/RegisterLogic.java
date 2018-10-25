package com.xuan.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author : xuan.
 * Date : 2018/6/1.
 * Description :用于注解presenter
 */
@Documented()
// 表示是基于编译时注解的
@Retention(RetentionPolicy.CLASS)
// 表示可以作用于成员变量，类、接口
@Target(ElementType.TYPE)
@Deprecated
public @interface RegisterLogic {
    //presenterId，逻辑id，唯一
    int value() default -1;
    //是否支持自动创建，默认支持只包含一个Context参数的构造函数，反射创建
    boolean auteCreate() default true;
}
