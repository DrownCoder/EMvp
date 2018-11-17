package com.xuan.complier;

import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ILogic;
import com.xuan.annotation.ViewInfo;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

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
    private int componentType = ViewInfo.TYPE_NONE;
    //ViewHolder的布局文件
    private int layoutId;
    //当不使用布局文件的时候，使用View注解的类名
    private String parentViewName;
    //是否自动创建，利用反射
    private boolean autoCreate;
    //注解需要获取的Presenter类名
    private String presenterName;
    //使用attach注解的绑定的使用类，也就是可用范围
    private String attachClassName;

    public ComponentTypeClassInfo(TypeElement classElement) {
        this.typeElement = classElement;
        ComponentType annotation = classElement.getAnnotation(ComponentType.class);
        componentId = annotation.value();
        autoCreate = annotation.autoCreate();
        layoutId = annotation.layout();
        try {
            Class parentClass = annotation.view();
            parentViewName = parentClass.getName();
        } catch (MirroredTypeException mte) {
            DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            parentViewName = classTypeElement.getQualifiedName().toString();
        }
        try {
            Class attachClass = annotation.attach();
            attachClassName = attachClass.getName();
        } catch (MirroredTypeException mte) {
            DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            attachClassName = classTypeElement.getQualifiedName().toString();
        }

        className = classElement.getQualifiedName().toString();
        if (typeElement.getAnnotation(ILogic.class) != null) {
            ILogic presenter = classElement.getAnnotation(ILogic.class);
            Class<?> presenterClass = null;
            try {
                presenterClass = presenter.value();
                presenterName = presenterClass.getName();
            } catch (MirroredTypeException mte) {
                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                presenterName = classTypeElement.getQualifiedName().toString();
            }
        }
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

    public String getPresenterClass(){
        return presenterName;
    }

    public String getParentViewName() {
        return parentViewName;
    }

    public String getAttachClassName() {
        return attachClassName;
    }

    public boolean isAttaching() {
        return getAttachClassName() != null
                && getAttachClassName().length() > 0
                && !getAttachClassName().equals(Object.class.getName());
    }
}


