package com.xuan.complier;

import com.google.auto.service.AutoService;
import com.xuan.annotation.BindType;
import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ILogic;
import com.xuan.annotation.ViewInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

import static com.xuan.complier.Const.*;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :type类型的注解解析器
 */
@AutoService(Processor.class)
public class TypeProcessor extends BaseProcessor {
    private List<ModelTypeClassInfo> typeModel = new ArrayList<>();
    //全局ComponentInfo
    private List<ComponentTypeClassInfo> globalTypeWidget = new ArrayList<>();
    //个人ComponentInfo
    private List<ComponentTypeClassInfo> attachTypeWidget = new ArrayList<>();
    private HashMap<Integer, Boolean> globalComponentIds = new HashMap<>();
    private HashMap<String, HashMap<Integer, Boolean>> attachComponentIds = new HashMap<>();
    protected static boolean hasProcessor;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<>();
        annotations.add(ComponentType.class.getCanonicalName());
        annotations.add(BindType.class.getCanonicalName());
        annotations.add(ILogic.class.getCanonicalName());
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (hasProcessor) {

            return true;
        }
        hasProcessor = true;
        System.out.println("------ process -----");
        //检查ComponentType注解
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ComponentType
                .class)) {
            checkClassValid(annotatedElement, ComponentType.class.getSimpleName());
            //检查被注解的类是否标准
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ComponentTypeClassInfo componentInfo = new ComponentTypeClassInfo(typeElement);
                if (!isValidComponent(componentInfo)) {
                    return true;
                }
                if (componentInfo.isAttaching()) {
                    attachId(componentInfo.getAttachClassName(), componentInfo.getComponentId());
                    attachTypeWidget.add(componentInfo);
                } else {
                    globalComponentIds.put(componentInfo.getComponentId(), true);
                    globalTypeWidget.add(componentInfo);
                }
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }
        //检查BindType注解
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(BindType.class)) {
            checkClassValid(annotatedElement, ComponentType.class.getSimpleName());
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ModelTypeClassInfo componentInfo = new ModelTypeClassInfo(typeElement);
                /*if (!isValidComponent(componentInfo)) {
                    return true;
                }*/
                if (componentInfo.getComponentId() > 0) {
                    typeModel.add(componentInfo);
                }
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }

        if (globalTypeWidget.size() > 0) {
            writeFile();
        }
        clear();
        return true;
    }

    private void attachId(String clazz, int componentId) {
        HashMap<Integer, Boolean> ids = attachComponentIds.get(clazz);
        if (ids == null) {
            ids = new HashMap<>();
            attachComponentIds.put(clazz, ids);
        }
        ids.put(componentId, true);
    }

    private boolean containAttachId(String clazz, int componentId) {
        HashMap<Integer, Boolean> ids = attachComponentIds.get(clazz);
        if (ids == null || ids.size() == 0) {
            return false;
        }
        Boolean contain = ids.get(componentId);
        return contain != null && contain;
    }

    private void clear() {
        typeModel.clear();
        globalTypeWidget.clear();
        globalComponentIds.clear();
        attachComponentIds.clear();
        attachTypeWidget.clear();
        strBuilder.setLength(0);
    }

    private void writeFile() {
        BufferedWriter writer = null;
        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(DIRECTORY_PATH
                    + "." + FILE_NAME_RULE_COMPONENT);
            writer = new BufferedWriter(sourceFile.openWriter());
            writer.write("package " + DIRECTORY_PATH + ";\n\n");
            writer.write("import android.util.SparseArray;\n");
            writer.write("import java.util.HashMap;\n");
            writer.write("import java.util.Map;\n");
            writer.write("import com.xuan.annotation.ViewInfo;\n\n");
            writer.write("public class " + FILE_NAME_RULE_COMPONENT + " implements IComponentRule" +
                    " " + " {\n");
            writer.write("    public static final SparseArray<ViewInfo> WIDGET_TYPE;\n\n");
            writer.write("    public static final Map<Class<?>, SparseArray<ViewInfo>> " +
                    "ATTACH_TYPE;\n\n");
            writer.write("    public static final Map<Class<?>, Integer> MODEL_TYPE;\n\n");
            writer.write("    static {\n");
            writer.write("        WIDGET_TYPE = new SparseArray<>();\n");
            writer.write("        MODEL_TYPE = new HashMap();\n\n");
            writer.write("        ATTACH_TYPE = new HashMap<>();\n\n");
            writePutWidgetLine(writer);
            writeAttachWidgetLine(writer);
            writePutModelLine(writer);
            writer.write("    }\n\n");
            writer.write("    private static void putWidget(int id,ViewInfo info) {\n");
            writer.write("        WIDGET_TYPE.put(id, info);\n");
            writer.write("    }\n\n");
            writer.write("    private static void putModel(Class<?> clazz, int id) {\n");
            writer.write("        MODEL_TYPE.put(clazz, id);;\n");
            writer.write("    }\n\n");
            writer.write("    private static void attachWidget(Class<?> clazz,int id,ViewInfo " +
                    "info){\n");
            writer.write("        SparseArray attachArray = ATTACH_TYPE.get(clazz);\n");
            writer.write("        if (attachArray == null) {\n");
            writer.write("            attachArray = new SparseArray();\n");
            writer.write("            ATTACH_TYPE.put(clazz, attachArray);\n");
            writer.write("        }\n");
            writer.write("        attachArray.put(id, info);\n");
            writer.write("    }\n\n");
            writer.write("    @Override\n");
            writer.write("    public ViewInfo obtainViewInfo(int id) {\n");
            writer.write("        return WIDGET_TYPE.get(id);\n");
            writer.write("    }\n\n");
            writer.write("    @Override\n");
            writer.write("    public int obtainComponentId(Class<?> clazz) {\n");
            writer.write("        return MODEL_TYPE.get(clazz);\n");
            writer.write("    }\n\n");
            writer.write("    @Override\n");
            writer.write("    public ViewInfo obtainAttachViewInfo(Class<?> clazz, int id) {\n");
            writer.write("        return ATTACH_TYPE.get(clazz).get(id);\n");
            writer.write("    }\n\n");
            writer.write("}\n");
        } catch (IOException e) {
            throw new RuntimeException("Could not write source for " + FILE_NAME_RULE_COMPONENT, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    //Silent
                }
            }
        }
    }

    private void writePutModelLine(BufferedWriter writer) throws IOException {
        strBuilder.setLength(0);
        for (ModelTypeClassInfo model : typeModel) {
            strBuilder.append("        putModel(").append(model.getClassName()).append(".class")
                    .append(",")
                    .append(model.getComponentId())
                    .append(");\n");
            writer.write(strBuilder.toString());
            strBuilder.setLength(0);
        }
    }

    private void writePutWidgetLine(BufferedWriter writer) throws IOException {
        strBuilder.setLength(0);
        for (ComponentTypeClassInfo info : globalTypeWidget) {
            strBuilder.append("        putWidget(").append(info.getComponentId()).append(",")
                    .append("new ViewInfo(").append(info.getComponentId()).append(",\n           " +
                    "     ")
                    .append(info.getClassName()).append(".class").append(",");
            if (info.getComponentType() == ViewInfo.TYPE_VIEW) {
                strBuilder.append(info.getComponentType());
            } else {
                strBuilder.append(info.getLayoutId()).append(",")
                        .append(info.getComponentType());
            }
            if (!info.isAutoCreate()) {
                strBuilder.append(", false");
            }
            if (info.getComponentType() != ViewInfo.TYPE_VIEW) {
                if (info.getParentViewName() != null
                        && info.getParentViewName().length() > 0
                        && !info.getParentViewName().equals(Object.class.getName())) {
                    strBuilder.append(",").append(info.getParentViewName()).append(".class");
                }
            }
            if (info.getPresenterClass() != null) {
                strBuilder.append(", ").append(info.getPresenterClass()).append(".class");
            } else {
                strBuilder.append(", null");
            }
            strBuilder.append("));\n");
            writer.write(strBuilder.toString());
            strBuilder.setLength(0);
        }
    }

    private void writeAttachWidgetLine(BufferedWriter writer) throws
            IOException {
        strBuilder.setLength(0);
        for (ComponentTypeClassInfo info : attachTypeWidget) {
            strBuilder.append("        attachWidget(")
                    .append(info.getAttachClassName()).append(".class").append(",")
                    .append(info.getComponentId()).append(",")
                    .append("new ViewInfo(").append(info.getComponentId()).append(",\n           " +
                    "     ")
                    .append(info.getClassName()).append(".class").append(",");
            if (info.getComponentType() == ViewInfo.TYPE_VIEW) {
                strBuilder.append(info.getComponentType());
            } else {
                strBuilder.append(info.getLayoutId()).append(",")
                        .append(info.getComponentType());
            }
            if (!info.isAutoCreate()) {
                strBuilder.append(", false");
            }
            if (info.getComponentType() != ViewInfo.TYPE_VIEW) {
                if (info.getParentViewName() != null
                        && info.getParentViewName().length() > 0
                        && !info.getParentViewName().equals(Object.class.getName())) {
                    strBuilder.append(",").append(info.getParentViewName()).append(".class");
                }
            }
            if (info.getPresenterClass() != null) {
                strBuilder.append(", ").append(info.getPresenterClass()).append(".class");
            } else {
                strBuilder.append(", null");
            }
            strBuilder.append("));\n");
            writer.write(strBuilder.toString());
            strBuilder.setLength(0);
        }
    }

    /**
     * 检查被ComponentType注解的类型是否符合要求：
     * 1.类是公有的
     * 2.类不能是抽象的
     * 3.组件id需要唯一，不能重复定义
     * 4.类需要继承View.class(自定义View)或者ViewHolder(RecyclerView.ViewHolder)
     * 5.其中注解了AutoCreate为true的自定义类需要提供默认构造函数View(Context context)
     * 6.类需要实现IComponentBind接口
     * 7.如果被注解的类是内部类，需要为静态内部类，不然没办法反射构造函数
     */
    private boolean isValidComponent(ComponentTypeClassInfo componentInfo) {
        TypeElement typeElement = componentInfo.getTypeElement();
        //类不是公有的
        if (!typeElement.getModifiers().contains(Modifier.PUBLIC)) {
            error(typeElement, "The class %s is not public." +
                            "\n被注解的类不能是私有的",
                    typeElement.getQualifiedName().toString());
            return false;
        }
        //类是抽象的
        if (typeElement.getModifiers().contains(Modifier.ABSTRACT)) {
            error(typeElement, "The class %s is abstract. You can't annotate abstract classes " +
                            "with %s" +
                            "\n被注解的组件不能是抽象类",
                    typeElement.getQualifiedName().toString(), ComponentTypeClassInfo.class
                            .getSimpleName());
            return false;
        }

        if (typeElement.getNestingKind().isNested() && !typeElement.getModifiers().contains
                (Modifier.STATIC)) {
            error(typeElement, "The class %s is inner but not a static class. You can't annotate " +
                            "abstract classes " +
                            "with %s" +
                            "\n被注解的类是内部类，需要声明为静态内部类",
                    typeElement.getQualifiedName().toString(), ComponentTypeClassInfo.class
                            .getSimpleName());
            return false;
        }

        if (componentInfo.getComponentId() == -1) {
            error(typeElement, "The Class %s annotated with %s must set value()" +
                            "\n 被注解的的类必须赋值ComponentId给value",
                    typeElement.getQualifiedName().toString(), ComponentType.class.getSimpleName());
            return false;
        }
        //必须实现IComponentBind接口
        /*if (typeElement.getInterfaces().size() <= 0) {
            error(typeElement, "The class %s must implement IComponentBind" +
                            "\n被注解的组件必须实现IComponentBind接口",
                    typeElement.getQualifiedName().toString());
            return false;
        }*/
        //组件id唯一
        if (componentInfo.isAttaching()) {
            //绑定模式
            if (containAttachId(componentInfo.getAttachClassName(), componentInfo.getComponentId
                    ())) {
                error(typeElement, "The ComponentId %s has been used in Attach Model " +
                        "%s\n该组件id已经被绑定到%s类了", componentInfo
                        .getComponentId(), componentInfo.getAttachClassName(), componentInfo
                        .getAttachClassName());
                return false;
            }
        } else {
            //全局模式
            Boolean contain = globalComponentIds.get(componentInfo.getComponentId());
            if (contain != null && contain) {
                error(typeElement, "The ComponentId %s has been used\n该组件id已经被全局使用过", componentInfo
                        .getComponentId());
                return false;
            }
        }

        //检查继承是否符合要求
        TypeElement currentClass = typeElement;
        while (true) {
            TypeMirror superClassType = currentClass.getSuperclass();
            if (superClassType.getKind() == TypeKind.NONE) {
                // 到达了基本类型(java.lang.Object), 所以退出
                error(typeElement, "The Class %s annotated with %s must extend from %s" +
                                "\n 被注解的的类必须是ViewHolder或者自定义View",
                        typeElement.getQualifiedName().toString(), ComponentType.class
                                .getSimpleName(),
                        getSupportClass());
                return false;
            }
            String superName = superClassType.toString();
            String type = isSupportView(superName);
            switch (type) {
                // 找到了要求的父类
                case SUPPORT_VIEW:
                    //继承的是View
                    componentInfo.setComponentType(ViewInfo.TYPE_VIEW);
                    break;
                case SUPPORT_HOLDER:
                    //继承的是ViewHolder
                    componentInfo.setComponentType(ViewInfo.TYPE_HOLDER);
                    break;
                case SUPPORT_COMPONENT:
                    //继承的是Component
                    componentInfo.setComponentType(ViewInfo.TYPE_COMPONENT);
                    break;
                default:
                    break;
            }
            if (componentInfo.getComponentType() != ViewInfo.TYPE_NONE) {
                break;
            }
            // 在继承树上继续向上搜寻
            currentClass = (TypeElement) typeUtils.asElement(superClassType);
        }
        if (componentInfo.getComponentType() == ViewInfo.TYPE_VIEW) {
            if (componentInfo.getParentViewName() != null
                    && componentInfo.getParentViewName().length() > 0
                    && !componentInfo.getParentViewName().equals(Object.class.getName())) {
                error(typeElement, "只有继承ViewHolder的类才能使用view属性",
                        typeElement.getQualifiedName().toString());
            }
        }
        //必须实现IComponentBind接口
        if (componentInfo.getComponentType() != ViewInfo.TYPE_COMPONENT) {
            //继承Component的类不需要实现IComponent接口，因为Component类实现类IComponentBind接口
            int i = 0;
            List<TypeMirror> interfaces = (List<TypeMirror>) typeElement.getInterfaces();
            TypeMirror currentInterface = interfaces.get(i);
            while (true) {
                if (currentInterface.toString().contains(IML_INTERFACE)) {
                    break;
                }
                if (i < interfaces.size()) {
                    currentInterface = interfaces.get(i++);
                } else {
                    error(typeElement, "The class %s must implement IComponentBind" +
                                    "\n被注解的组件必须实现IComponentBind接口",
                            typeElement.getQualifiedName().toString());
                    return false;
                }
            }
        }

        if (componentInfo.isAutoCreate() && componentInfo.getComponentType() == ViewInfo
                .TYPE_VIEW) {
            //如果需要自动创建类，自定义View需要实现默认只包含一个参数的构造函数，因为可能需要使用反射创建对象
            for (Element enclosed : typeElement.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                    ExecutableElement constructorElement = (ExecutableElement) enclosed;
                    if (constructorElement.getParameters().size() == 1
                            && constructorElement.getModifiers().contains(Modifier.PUBLIC)
                            && constructorElement.getParameters().get(0).toString().equals
                            ("context")) {
                        // 找到了默认构造函数
                        return true;
                    } else {
                        error(typeElement, "The Class %s annotated with %s must have a " +
                                        "Constructor(Context context)" +
                                        "\n被注解的类当autoCreate为true时需要有一个以Context为参数的默认构造函数，因为可能需要使用反射创建对象",
                                typeElement.getQualifiedName().toString(), ComponentType.class
                                        .getSimpleName());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 检测被注解对类是否是可以支持对类型，目前只支持继承ViewHolder,或者继承View，或者继承Componet<T>
     */
    private String isSupportView(String s) {
        String type = "";
        for (String spStr : SUPPORT_CLASS) {
            if (spStr.equals(s) || s.startsWith(spStr)) {
                type = spStr;
                return type;
            }
        }
        return type;
    }

    private String getSupportClass() {
        StringBuilder builder = new StringBuilder(128);
        for (String spStr : SUPPORT_CLASS) {
            builder.append(spStr).append(" Or ");
        }
        return builder.subSequence(0, builder.length() - 4).toString();
    }
}
