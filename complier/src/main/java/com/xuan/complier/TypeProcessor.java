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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
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
    private static final String CREATE_FILE_NAME = "ComponentRule";
    private static final String CREATE_FILE_PATH = "com.study.xuan.emvp";
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
        strBuilder = new StringBuilder();
        super.init(processingEnvironment);
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
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ComponentType.class)) {
            checkClassValid(annotatedElement, ComponentType.class.getSimpleName());
            //检查被注解的类是否标准
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ComponentTypeClassInfo componentInfo = new ComponentTypeClassInfo(typeElement);
                /*if (!isValidClass(componentInfo)) {
                    return true;
                }*/
                componentIds.add(componentInfo.getComponentId());
                typeWidget.add(componentInfo);
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(BindType.class)) {
            checkClassValid(annotatedElement, ComponentType.class.getSimpleName());
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ModelTypeClassInfo componentInfo = new ModelTypeClassInfo(typeElement);
                /*if (!isValidClass(componentInfo)) {
                    return true;
                }*/
                typeModel.add(componentInfo);
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }
        if (typeWidget.size() > 0) {
            writeFile();
        }
        clear();
        return false;
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
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(CREATE_FILE_NAME);
            writer = new BufferedWriter(sourceFile.openWriter());
            writer.write("package " + CREATE_FILE_PATH + ";\n\n");
            writer.write("import android.util.SparseArray;\n");
            writer.write("import java.util.HashMap;\n");
            writer.write("import java.util.Map;");
            writer.write("import com.xuan.annotation.ViewInfo;\n");
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
            if (info.getComponentType() == ComponentType.Support.View) {
                strBuilder.append(ViewInfo.TYPE_VIEW);
            } else {
                strBuilder.append(info.getLayoutId()).append(",")
                        .append(ViewInfo.TYPE_VIEWHOLDER);
            }
            strBuilder.append("));\n");
            writer.write(strBuilder.toString());
            strBuilder.setLength(0);
        }
    }

    private boolean isValidClass(ComponentTypeClassInfo componentInfo) {
        TypeElement typeElement = componentInfo.getTypeElement();
        //类不是公有的
        if (!typeElement.getModifiers().contains(Modifier.PUBLIC)) {
            error(typeElement, "The class %s is not public.",
                    typeElement.getQualifiedName().toString());
            return false;
        }
        //类是抽象的
        if (typeElement.getModifiers().contains(Modifier.ABSTRACT)) {
            error(typeElement, "The class %s is abstract. You can't annotate abstract classes with @%",
                    typeElement.getQualifiedName().toString(), ComponentTypeClassInfo.class.getSimpleName());
            return false;
        }
        //组件id唯一
        if (componentIds.contains(componentInfo.getComponentId())) {
            error(typeElement, "The ComponentType %s has been used", componentInfo.getComponentId());
            return false;
        }
        return false;
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
