package com.example.a14512.smallnote;

import android.app.Application;
import android.content.Context;

/**
 * @author 14512 on 2018/11/5
 */
public class NoteApplication extends Application {
    private static Context sContext;
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public static Context getContext() {
        return sContext;
    }
}
