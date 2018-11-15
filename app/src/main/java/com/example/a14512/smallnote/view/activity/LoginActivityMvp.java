package com.example.a14512.smallnote.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.a14512.smallnote.base.BaseActivityMvp;
import com.example.a14512.smallnote.presenter.LoginPresenter;
import com.example.a14512.smallnote.view.ILoginView;

/**
 * @author 14512 on 2018/11/5
 */
public class LoginActivityMvp extends BaseActivityMvp<LoginPresenter> implements ILoginView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}
