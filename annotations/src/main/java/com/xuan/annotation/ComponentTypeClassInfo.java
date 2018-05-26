package com.xuan.annotation;

import javax.lang.model.element.TypeElement;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :使用ComponentType注解的类的信息
 */

public class ComponentTypeClassInfo {
    private TypeElement typeElement;
    //组件id全局唯一
    private int componentId;
    //被注解类的全类名
    private String className;
    //View类型 ViewHolder或者自定义View
    private int componentType;
    private int layoutId;
    private boolean autoCreate;

    public ComponentTypeClassInfo(TypeElement classElement) {
        this.typeElement = classElement;
        ComponentType annotation = classElement.getAnnotation(ComponentType.class);
        componentId = annotation.value();
        autoCreate = annotation.autoCreate();
        layoutId = annotation.layout();
        System.out.println("getQualifiedName = " + classElement.getQualifiedName());
        System.out.println("getSimpleName = " + classElement.getSimpleName());
        System.out.println("getSuperClass = " + classElement.getSuperclass());
        System.out.println("getClass = " + classElement.getClass());

        className = classElement.getQualifiedName().toString();
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
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

    public int getComponentType() {
        return componentType;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public boolean isAutoCreate() {
        return autoCreate;
    }
}


