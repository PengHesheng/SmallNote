package com.example.a14512.smallnote.presenter;

import com.example.a14512.smallnote.base.BasePresenter;
import com.example.a14512.smallnote.mode.RecycleMode;
import com.example.a14512.smallnote.view.IRecycleView;

/**
 * @author 14512 on 2018/11/5
 */
public class RecyclePresenter extends BasePresenter<RecycleMode, IRecycleView> {

    public RecyclePresenter(IRecycleView view) {
        super(view);
    }

    @Override
    protected RecycleMode initMode() {
        return new RecycleMode();
    }

    public void getNotes() {

    }
}
