package com.xuan.eapi.utils;

import android.util.Log;

/**
 * Created by xuan on 2018/5/5.
 */

public class LogUtil {
    private static final String LOG_T = "------------";
    public static void Log(String log) {
        Log.i(LOG_T, log);
    }

    public static void Error(String log) {
        Log.e(LOG_T, log);
    }
}
