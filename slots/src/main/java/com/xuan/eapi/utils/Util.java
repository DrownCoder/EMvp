package com.xuan.eapi.utils;

import android.os.Looper;

/**
 * Author : xuan.
 * Date : 2018/8/23.
 * Description :the description of this file
 */

public class Util {
    /**
     * Returns {@code true} if called on the main thread, {@code false} otherwise.
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
