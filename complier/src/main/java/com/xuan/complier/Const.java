package com.xuan.complier;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/4.
 * Description :解析器的常量
 */

public class Const {
    public static final String DIRECTORY_PATH = "com.xuan.eapi.rule";
    public static final String FILE_NAME_RULE_COMPONENT = "ComponentRule";
    public static final String FILE_NAME_RULE_PRESENTER = "PresenterRule";
    public static final String CREATE_FILE_PATH = "com.study.xuan.emvp";
    public static final List<String> SUPPORT_CLASS = new ArrayList<>();
    public static final String IML_INTERFACE = "IComponentBind";

    public static final String SUPPORT_VIEW = "android.view.View";
    public static final String SUPPORT_HOLDER = "android.support.v7.widget.RecyclerView.ViewHolder";
    public static final String SUPPORT_COMPONENT = "com.xuan.eapi.component.Component";
    static {
        SUPPORT_CLASS.add(SUPPORT_VIEW);
        SUPPORT_CLASS.add(SUPPORT_HOLDER);
        SUPPORT_CLASS.add(SUPPORT_COMPONENT);
    }
}
