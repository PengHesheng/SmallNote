package com.example.a14512.smallnote.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 14512 on 2018/11/5
 */
public abstract class BaseFragmentMvp<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * create presenter
     * @return
     */
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
