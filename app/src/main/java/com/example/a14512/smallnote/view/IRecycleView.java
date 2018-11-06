package com.example.a14512.smallnote.view;

import com.example.a14512.smallnote.base.IBaseView;
import com.example.a14512.smallnote.mode.Note;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/11/5
 */
public interface IRecycleView extends IBaseView {

    /**
     * @param notes
     */
    void setNotes(ArrayList<Note> notes);
}
