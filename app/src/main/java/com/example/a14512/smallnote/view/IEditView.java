package com.example.a14512.smallnote.view;

import com.example.a14512.smallnote.base.IBaseView;

/**
 * @author 14512 on 2018/11/5
 */
public interface IEditView extends IBaseView {

    /**
     * note title
     * @return
     */
    String getNoteTitle();
    /**
     * note content
     * @return
     */
    String getNoteContent();

    /**
     * set title
     * @param title
     */
    void setNoteTitle(String title);

    /**
     * set content
     * @param content
     */
    void setNoteContent(String content);
}
