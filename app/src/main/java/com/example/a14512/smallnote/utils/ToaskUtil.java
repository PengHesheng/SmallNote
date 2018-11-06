package com.example.a14512.smallnote.utils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * @author 14512 on 2018/11/5
 */
public final class ToaskUtil {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(String msg) {
        show();
    }
}
