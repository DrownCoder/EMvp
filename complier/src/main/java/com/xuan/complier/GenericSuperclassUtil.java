package com.xuan.complier;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;  
  
public class GenericSuperclassUtil {  
  
    /*  
     * 获取泛型类Class对象，不是泛型类则返回null  
     */  
    public static Class<?> getActualTypeArgument(Class<?> clazz) {  
        Class<?> entitiClass = null;  
        Type genericSuperclass = clazz.getGenericSuperclass();  
        if (genericSuperclass instanceof ParameterizedType) {  
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)  
                    .getActualTypeArguments();  
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {  
                entitiClass = (Class<?>) actualTypeArguments[0];  
            }  
        }  
  
        return entitiClass;  
    }

    /** 获取接口的泛型类型 */
    public static Class<?> getParameterizedType(Class<?> clazz) {
        try {
            Type[] a = clazz.getGenericInterfaces();
            if (a != null && a.length > 0) {
                for (Type type : a) {
                    if (type instanceof ParameterizedType) {
                        ParameterizedType type1 = (ParameterizedType) type;
                        Class<?> cal = (Class<?>) type1
                                .getActualTypeArguments()[0];
                        return cal;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  
}  