package com.example.a14512.smallnote.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.a14512.smallnote.NoteApplication;

/**
 * @author 14512 on 2018/11/5
 */
public final class ToastUtil {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(String msg) {
        show(NoteApplication.getContext(), msg);
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String msg) {
        showLong(NoteApplication.getContext(), msg);
    }
}
