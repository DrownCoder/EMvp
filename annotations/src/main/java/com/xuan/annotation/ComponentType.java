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
    //ComponentId
    int value() default -1;
    //LayoutId，当为ViewHolder类型需要
    int layout() default -1;
    //是否利用反射创建，默认打开的(复杂的，性能相关的，数量大的当然建议关闭咯)
    boolean autoCreate() default true;
}
