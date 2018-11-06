package com.example.a14512.smallnote.view;

import com.example.a14512.smallnote.base.IBaseView;
import com.example.a14512.smallnote.mode.Note;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/11/4
 */
public interface IAllNotesView extends IBaseView {

    /**
     * @param notes
     */
    void setDatas(ArrayList<Note> notes);
}
