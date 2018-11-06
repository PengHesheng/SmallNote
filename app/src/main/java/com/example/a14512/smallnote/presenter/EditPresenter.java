package com.example.a14512.smallnote.presenter;

import com.example.a14512.smallnote.base.BasePresenter;
import com.example.a14512.smallnote.mode.EditMode;
import com.example.a14512.smallnote.view.IEditView;

/**
 * @author 14512 on 2018/11/5
 */
public class EditPresenter extends BasePresenter<EditMode, IEditView> {

    public EditPresenter(IEditView view) {
        super(view);
    }

    @Override
    protected EditMode initMode() {
        return new EditMode();
    }

    public void repeal() {

    }

    public void recovery() {

    }

    public void save() {

    }
}
