package com.example.a14512.smallnote.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author 14512 on 2018/11/1
 */
public abstract class BaseActivityMvp<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    /**
     * init presenter
     * @return presenter
     */
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
