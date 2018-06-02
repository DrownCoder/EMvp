package com.xuan.complier;

import com.google.auto.service.AutoService;
import com.xuan.annotation.ComponentTypeClassInfo;
import com.xuan.annotation.ModelTypeClassInfo;
import com.xuan.annotation.RegisterLogic;

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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Author : xuan.
 * Date : 2018/6/2.
 * Description :逻辑注解解析器
 */
@AutoService(Processor.class)
public class LogicProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private List<ModelTypeClassInfo> typeModel = new ArrayList<>();
    private List<ComponentTypeClassInfo> typeWidget = new ArrayList<>();
    private List<Integer> componentIds = new ArrayList<>();
    private StringBuilder strBuilder;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<>();
        annotations.add(RegisterLogic.class.getCanonicalName());
        return annotations;
    }

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
}
