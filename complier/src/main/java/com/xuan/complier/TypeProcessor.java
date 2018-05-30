package com.xuan.complier;

import com.google.auto.service.AutoService;
import com.xuan.annotation.BindType;
import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ComponentTypeClassInfo;
import com.xuan.annotation.ModelTypeClassInfo;
import com.xuan.annotation.ViewInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Author : xuan.
 * Date : 2018/5/15.
 * Description :type类型的注解解析器
 */
@AutoService(Processor.class)
public class TypeProcessor extends AbstractProcessor {
    private static final String DIRECTORY_PATH = "com.xuan.eapi.rule";
    private static final String CREATE_FILE_NAME = "ComponentRule";
    private static final String CREATE_FILE_PATH = "com.study.xuan.emvp";
    private static final String[] SUPPORT_CLASS = new String[]{
            "android.view.View",
            "android.support.v7.widget.RecyclerView.ViewHolder"
    };
    private static final String IML_INTERFACE = "IComponentBind";

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private List<ModelTypeClassInfo> typeModel = new ArrayList<>();
    private List<ComponentTypeClassInfo> typeWidget = new ArrayList<>();
    private List<Integer> componentIds = new ArrayList<>();
    private StringBuilder strBuilder;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        System.out.println("------ init -----");
        super.init(processingEnvironment);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        strBuilder = new StringBuilder();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<>();
        annotations.add(ComponentType.class.getCanonicalName());
        annotations.add(BindType.class.getCanonicalName());
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("------ process -----");
        //检查ComponentType注解
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ComponentType.class)) {
            checkClassValid(annotatedElement, ComponentType.class.getSimpleName());
            //检查被注解的类是否标准
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ComponentTypeClassInfo componentInfo = new ComponentTypeClassInfo(typeElement);
                if (!isValidComponent(componentInfo)) {
                    return true;
                }
                componentIds.add(componentInfo.getComponentId());
                typeWidget.add(componentInfo);
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
                if (componentInfo.getComponentIds().length == 1) {
                    typeModel.add(componentInfo);
                }
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }
        if (typeWidget.size() > 0) {
            writeFile();
        }
        clear();
        return true;
    }

    private void clear() {
        typeModel.clear();
        typeWidget.clear();
        componentIds.clear();
        strBuilder.setLength(0);
    }

    // 检查被注解为的元素是否是一个类
    private void checkClassValid(Element annotatedElement, String className) {
        if (annotatedElement.getKind() != ElementKind.CLASS) {
            error(annotatedElement, "Only classes can be annotated with @%s", className);
        }
    }

    private void writeFile() {
        BufferedWriter writer = null;
        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(DIRECTORY_PATH+"."+CREATE_FILE_NAME);
            writer = new BufferedWriter(sourceFile.openWriter());
            writer.write("package " + DIRECTORY_PATH + ";\n\n");
            writer.write("import android.util.SparseArray;\n");
            writer.write("import java.util.HashMap;\n");
            writer.write("import java.util.Map;\n");
            writer.write("import com.xuan.annotation.ViewInfo;\n\n");
            writer.write("public class " + CREATE_FILE_NAME + " {\n");
            writer.write("    public static final SparseArray<ViewInfo> WIDGET_TYPE;\n\n");
            writer.write("    public static final Map<Class<?>, Integer> MODEL_TYPE;\n\n");
            writer.write("    static {\n");
            writer.write("        WIDGET_TYPE = new SparseArray<>();\n");
            writer.write("        MODEL_TYPE = new HashMap();\n\n");
            writePutWidgetLine(writer);
            writePutModelLine(writer);
            writer.write("    }\n\n");
            writer.write("    private static void putWidget(int id,ViewInfo info) {\n");
            writer.write("        WIDGET_TYPE.put(id, info);\n");
            writer.write("    }\n\n");
            writer.write("    private static void putModel(Class<?> clazz, int id) {\n");
            writer.write("        MODEL_TYPE.put(clazz, id);;\n");
            writer.write("    }\n\n");
            writer.write("}\n");
        } catch (IOException e) {
            throw new RuntimeException("Could not write source for " + CREATE_FILE_NAME, e);
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
            strBuilder.append("        putModel(").append(model.getClassName()).append(".class").append(",")
                    .append(model.getComponentIds()[0])
                    .append(");\n");
            writer.write(strBuilder.toString());
            strBuilder.setLength(0);
        }
    }

    private void writePutWidgetLine(BufferedWriter writer) throws IOException {
        strBuilder.setLength(0);
        for (ComponentTypeClassInfo info : typeWidget) {
            strBuilder.append("        putWidget(").append(info.getComponentId()).append(",")
                    .append("new ViewInfo(").append(info.getComponentId()).append(",\n                ")
                    .append(info.getClassName()).append(".class").append(",");
            if (info.getComponentType() == ViewInfo.TYPE_VIEW) {
                strBuilder.append(ViewInfo.TYPE_VIEW);
            } else {
                strBuilder.append(info.getLayoutId()).append(",")
                        .append(ViewInfo.TYPE_VIEWHOLDER);
            }
            if (!info.isAutoCreate()) {
                strBuilder.append(", false");
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
     * 5.其中自定义类需要提供默认构造函数View(Context context)
     * 6.类需要实现IComponentBind接口
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
            error(typeElement, "The class %s is abstract. You can't annotate abstract classes with @%" +
                            "\n被注解的组件不能是抽象类",
                    typeElement.getQualifiedName().toString(), ComponentTypeClassInfo.class.getSimpleName());
            return false;
        }
        if (componentInfo.getComponentId() == -1) {
            error(typeElement, "The Class %s annotated with @%s must set value()" +
                            "\n 被注解的的类必须赋值ComponentId给value",
                    typeElement.getQualifiedName().toString(), ComponentType.class.getSimpleName());
            return false;
        }
        //必须实现IComponentBind接口
        if (typeElement.getInterfaces().size() <= 0) {
            error(typeElement, "The class %s must implement IComponentBind" +
                            "\n被注解的组件必须实现IComponentBind接口",
                    typeElement.getQualifiedName().toString());
            return false;
        }
        //组件id唯一
        if (componentIds.contains(componentInfo.getComponentId())) {
            error(typeElement, "The ComponentId %s has been used\n该组件id已经被使用过", componentInfo.getComponentId());
            return false;
        }
        //检查继承是否符合要求
        TypeElement currentClass = typeElement;
        while (true) {
            TypeMirror superClassType = currentClass.getSuperclass();
            if (superClassType.getKind() == TypeKind.NONE) {
                // 到达了基本类型(java.lang.Object), 所以退出
                error(typeElement, "The Class %s annotated with @%s must extend from %s" +
                                "\n 被注解的的类必须是ViewHolder或者自定义View",
                        typeElement.getQualifiedName().toString(), ComponentType.class.getSimpleName(),
                        getSupportClass());
                return false;
            }
            if (isSupportView(superClassType.toString())) {
                // 找到了要求的父类
                if (superClassType.toString().equals(SUPPORT_CLASS[0])) {
                    componentInfo.setComponentType(ViewInfo.TYPE_VIEW);
                } else if (superClassType.toString().equals(SUPPORT_CLASS[1])) {
                    componentInfo.setComponentType(ViewInfo.TYPE_VIEWHOLDER);
                }
                break;
            }
            // 在继承树上继续向上搜寻
            currentClass = (TypeElement) typeUtils.asElement(superClassType);
        }
        //必须实现IComponentBind接口
        int i = 0;
        List<TypeMirror> interfaces = (List<TypeMirror>) typeElement.getInterfaces();
        TypeMirror currentInterface = interfaces.get(i);
        while (true) {
            if (currentInterface.toString().contains(IML_INTERFACE)) {
                break;
            }
            if (i < interfaces.size()) {
                currentInterface = interfaces.get(i++);
            }else{
                error(typeElement, "The class %s must implement IComponentBind" +
                                "\n被注解的组件必须实现IComponentBind接口",
                        typeElement.getQualifiedName().toString());
                return false;
            }
        }
        if (componentInfo.isAutoCreate() && componentInfo.getComponentType() == ViewInfo.TYPE_VIEW) {
            //如果需要自动创建类，自定义View需要实现默认只包含一个参数的构造函数，因为可能需要使用反射创建对象
            for (Element enclosed : typeElement.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                    ExecutableElement constructorElement = (ExecutableElement) enclosed;
                    if (constructorElement.getParameters().size() == 1
                            && constructorElement.getModifiers().contains(Modifier.PUBLIC)
                            && constructorElement.getParameters().get(0).toString().equals("context")) {
                        // 找到了默认构造函数
                        return true;
                    } else {
                        error(typeElement, "The Class %s annotated with @%s must have a Constructor(Context context)" +
                                        "\n被注解的类当autoCreate为true时需要有一个以Context为参数的默认构造函数，因为可能需要使用反射创建对象",
                                typeElement.getQualifiedName().toString(), ComponentType.class.getSimpleName());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isSupportView(String s) {
        for (String spStr : SUPPORT_CLASS) {
            if (spStr.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private String getSupportClass() {
        StringBuilder builder = new StringBuilder(128);
        for (String spStr : SUPPORT_CLASS) {
            builder.append(spStr).append(" Or ");
        }
        return builder.subSequence(0, builder.length() - 4).toString();
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
