package com.example.a14512.smallnote.mode;

import android.content.Context;

import com.example.a14512.smallnote.C;
import com.example.a14512.smallnote.base.IBaseMode;
import com.example.a14512.smallnote.utils.ACache;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/11/4
 */
public class AllNotesMode implements IBaseMode {

    public ArrayList<Note> getNotes(Context context) {
        return (ArrayList<Note>) ACache.get(context).getAsObject(C.NOTE_CACHE);
    }
}
