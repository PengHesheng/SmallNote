package com.example.a14512.smallnote;

import android.app.Application;
import android.content.Context;

/**
 * @author 14512 on 2018/11/5
 */
public class NoteApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
