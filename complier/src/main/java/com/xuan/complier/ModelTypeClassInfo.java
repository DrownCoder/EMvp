package com.xuan.complier;

import com.xuan.annotation.BindType;

import javax.lang.model.element.TypeElement;

/**
 * Author : xuan.
 * Date : 2018/5/22.
 * Description :使用BindType注解的类的信息
 */

public class ModelTypeClassInfo {
    private TypeElement typeElement;
    //组件id全局唯一
    private int componentId;
    //被注解类的全类名
    private String className;

    public ModelTypeClassInfo(TypeElement classElement) {
        this.typeElement = classElement;
        BindType annotation = classElement.getAnnotation(BindType.class);
        componentId = annotation.value();
        if (componentId < 0) {
            throw new IllegalArgumentException(
                    String.format("value() in @%s for class %s is empty! that's not allowed",
                            BindType.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        System.out.println("getQualifiedName = " + classElement.getQualifiedName());
        System.out.println("getSimpleName = " + classElement.getSimpleName());
        System.out.println("getSuperClass = " + classElement.getSuperclass());
        System.out.println("getClass = " + classElement.getClass());

        className = classElement.getQualifiedName().toString();
    }

    public int getComponentId() {
        return componentId;
    }

    public String getClassName() {
        return className;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }
}


