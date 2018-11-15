package com.example.a14512.smallnote.view;

import com.example.a14512.smallnote.base.IBaseView;
import com.example.a14512.smallnote.mode.Note;

/**
 * @author 14512 on 2018/11/12
 */
public interface IDetailView extends IBaseView {

    /**
     * show note
     * @param note
     */
    void showNote(Note note);
}
