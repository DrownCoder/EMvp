package com.xuan.complier;

import com.google.auto.service.AutoService;
import com.xuan.annotation.RegisterLogic;

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
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :逻辑注解解析器
 */
@AutoService(Processor.class)
public class LogicProcessor extends BaseProcessor {
    private HashMap<Integer,String> presenterIds = new HashMap();
    private StringBuilder strBuilder;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(RegisterLogic.class)) {
            checkClassValid(annotatedElement, RegisterLogic.class.getSimpleName());
            //检查被注解的类是否标准
            try {
                TypeElement typeElement = (TypeElement) annotatedElement;
                RegisterLogic annotate = typeElement.getAnnotation(RegisterLogic.class);
                presenterIds.put(annotate.value(), typeElement.getQualifiedName().toString());
            } catch (Exception e) {
                error(annotatedElement, e.getMessage());
                return true;
            }
            if (presenterIds.size() > 0) {
                writeFile();
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<>();
        annotations.add(RegisterLogic.class.getCanonicalName());
        return annotations;
    }

    private void writeFile() {
        BufferedWriter writer = null;
        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(Const.DIRECTORY_PATH + "." + Const.FILE_NAME_RULE_PRESENTER);
            writer = new BufferedWriter(sourceFile.openWriter());
            writer.write("package " + Const.DIRECTORY_PATH + ";\n\n");
            writer.write("import android.util.SparseArray;\n\n");
            writer.write("public class " + Const.FILE_NAME_RULE_PRESENTER + " implements IPresenterRule " + " {\n");
            writer.write("    public static final SparseArray<Class<?>> PRESENTER_RULE;\n\n");
            writer.write("    static {\n");
            writer.write("        PRESENTER_RULE = new SparseArray<>();\n\n");
            writer.write("    }\n\n");
            writer.write("    private static void putPresenter(int id,Class<?> clazz) {\n");
            writer.write("        PRESENTER_RULE.put(id, clazz);\n");
            writer.write("    }\n\n");
            writer.write("    @Override\n");
            writer.write("    public ViewInfo obtainViewInfo(int id) {\n");
            writer.write("        return WIDGET_TYPE.get(id);\n");
            writer.write("    }\n\n");
            writer.write("    @Override\n");
            writer.write("    public int obtainComponentId(Class<?> clazz) {\n");
            writer.write("        return MODEL_TYPE.get(clazz);\n");
            writer.write("    }\n\n");
            writer.write("}\n");
        } catch (IOException e) {
            throw new RuntimeException("Could not write source for " + Const.FILE_NAME_RULE_PRESENTER, e);
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
}
