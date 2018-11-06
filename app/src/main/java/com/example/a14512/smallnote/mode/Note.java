package com.example.a14512.smallnote.mode;

import android.os.SystemClock;

import java.io.Serializable;

/**
 * 便签类
 * @author 14512 on 2018/11/4
 */
public class Note implements Serializable {
    private String mTitle = null;
    private String mContent = null;
    private long mDate;

    public Note() {
        mDate = SystemClock.currentThreadTimeMillis();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public long getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return mTitle + "\n" + mContent + "\n" + mDate;
    }
}
