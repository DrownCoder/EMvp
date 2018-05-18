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
    private int componentType;
    //父类，Object下的一级父类，只允许继承ViewHolder和View的类使用注解
    private Class superClass;

    public ComponentTypeClassInfo(TypeElement classElement) {
        this.typeElement = classElement;
        ComponentType annotation = classElement.getAnnotation(ComponentType.class);
        componentType = annotation.value();
        if (componentType < 0) {
            throw new IllegalArgumentException(
                    String.format("ComponentType() in @%s for class %s is negative num! that's not allowed",
                            ComponentType.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        superClass = classElement.getClass();
        while (superClass.getSuperclass() != null) {
            if (superClass.getSuperclass() != Object.class) {
                superClass = superClass.getSuperclass();
            }
        }
        if (!(superClass.getName().contains("RecyclerView.ViewHolder") || superClass.getName().contains("android.View"))){
            throw new IllegalArgumentException("ComponentType() can only be used in ViewHolder or View");
        }
    }

    public int getComponentType() {
        return componentType;
    }

    public Class getSuperClass() {
        return superClass;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }
}


