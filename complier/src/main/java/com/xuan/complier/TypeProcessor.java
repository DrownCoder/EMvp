package com.xuan.complier;

import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ComponentTypeClassInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
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
public class TypeProcessor extends AbstractProcessor {
    private static final String CREATE_FILE_NAME = "ComponentRule";
    private static final String CREATE_FILE_PATH = "com.study.xuan.emvp";
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private Map<Integer, Class> typeModel = new LinkedHashMap<Integer, Class>();
    private Map<Integer, Class> typeWidget = new LinkedHashMap<Integer, Class>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
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
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ComponentType.class)) {
            // 检查被注解为@Factory的元素是否是一个类
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error(annotatedElement, "Only classes can be annotated with @%s",
                        ComponentType.class.getSimpleName());
            }
            //检查被注解的类是否标准
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                ComponentTypeClassInfo componentInfo = new ComponentTypeClassInfo(typeElement);
                if (!isValidClass(componentInfo)) {
                    return true;
                }
                typeWidget.put(componentInfo.getComponentType(), typeElement.getClass());
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
        }
        if (typeWidget.size() > 0) {
            writeFile();
        }
        return true;
    }

    private void writeFile() {
        BufferedWriter writer = null;
        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(CREATE_FILE_NAME);
            writer = new BufferedWriter(sourceFile.openWriter());
            writer.write("package " + CREATE_FILE_PATH + ";\n\n");
            writer.write("import android.util.SparseArray;\n");
            writer.write("public class " + CREATE_FILE_NAME + " {\n");
            writer.write("    public static final SparseArray<Object> WIDGET_TYPE;\n\n");
            writer.write("    static {\n");
            writer.write("        WIDGET_TYPE = new SparseArray<>();\n\n");
            writePutLine(writer);
            writer.write("    }\n\n");
            writer.write("    private static void putWidget(int id,Class<?> classz) {\n");
            writer.write("        WIDGET_TYPE.put(id, classz);\n");
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

    private void writePutLine(BufferedWriter writer) throws IOException {
        for (Map.Entry<Integer, Class> entry : typeWidget.entrySet()) {
            writer.write("putWidget(" + entry.getKey() + ",+" + entry.getValue() + ".class);");
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
        if (typeWidget.containsKey(componentInfo.getComponentType())) {
            error(typeElement, "The ComponentType %s has been used", componentInfo.getComponentType());
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
