package com.example.a14512.smallnote.presenter;

import com.example.a14512.smallnote.base.BasePresenter;
import com.example.a14512.smallnote.mode.LoginMode;
import com.example.a14512.smallnote.view.ILoginView;

/**
 * @author 14512 on 2018/11/5
 */
public class LoginPresenter extends BasePresenter<LoginMode, ILoginView> {

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    @Override
    protected LoginMode initMode() {
        return new LoginMode();
    }
}
