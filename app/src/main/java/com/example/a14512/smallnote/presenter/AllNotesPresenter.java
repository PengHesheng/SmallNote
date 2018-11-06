package com.example.a14512.smallnote.presenter;

import com.example.a14512.smallnote.base.BasePresenter;
import com.example.a14512.smallnote.mode.AllNotesMode;
import com.example.a14512.smallnote.view.IAllNotesView;

/**
 * @author 14512 on 2018/11/4
 */
public class AllNotesPresenter extends BasePresenter<AllNotesMode, IAllNotesView> {

    public AllNotesPresenter(IAllNotesView view) {
        super(view);
    }

    @Override
    protected AllNotesMode initMode() {
        return new AllNotesMode();
    }

    public void getNotes() {
        mView.setDatas(mMode.getNotes());
    }


}