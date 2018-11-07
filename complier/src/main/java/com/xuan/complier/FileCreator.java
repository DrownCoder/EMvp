package com.xuan.complier;

/**
 * Author : xuan.
 * Date : 2018/11/7.
 * Description :分割方法，防止64k异常
 */
public class FileCreator {
    public static final String GLOBAL_METHOD_INVOKE = "        splitGlobalMethodStep%s();\n";
    public static final String GLOBAL_METHOD_T = "    public static void splitGlobalMethodStep%s(){\n%s}\n\n";
}
