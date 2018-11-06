package com.example.a14512.smallnote.utils;

import android.util.Log;

/**
 * @author 14512 on 2018/11/4
 */
public final class LogUtil {

    public static int v(String msg) {
        return v(getClassName(), msg);
    }

    public static int v(String tag, String msg) {
        return Log.v(tag, msg);
    }

    public static int d(String msg) {
        return d(getClassName(), msg);
    }

    public static int d(String tag, String msg) {
        return Log.d(tag, msg);
    }

    public static int i(String msg) {
        return i(getClassName(), msg);
    }

    public static int i(String tag, String msg) {
        return Log.i(tag, msg);
    }

    public static int w(String msg) {
        return w(getClassName(), msg);
    }

    public static int w(String tag, String msg) {
        return Log.w(tag, msg);
    }

    public static int e(String msg) {
        return e(getClassName(), msg);
    }

    public static int e(String tag, String msg) {
        return Log.e(tag, msg);
    }

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[1].getClassName();
    }
}
