package com.xuan.complier;

/**
 * Author : xuan.
 * Date : 2018/11/7.
 * Description :分割方法，防止64k异常
 */
public class FileCreator {
    public static final String COMMON_METHOD_INVOKE = "        splitCommonMethodStep%s();\n";
    public static final String COMMON_METHOD_T = "    public static void splitCommonMethodStep%s(){\n%s}\n\n";

    public static final String ATTACH_METHOD_INVOKE = "        splitAttachMethodStep%s();\n";
    public static final String ATTACH_METHOD_T = "    public static void splitAttachMethodStep%s(){\n%s}\n\n";
}
