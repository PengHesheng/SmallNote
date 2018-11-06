package com.example.a14512.smallnote.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 14512 on 2018/11/5
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mPresenter = createPresenter();
        initView(view);
        return view;
    }

    /**
     * create presenter
     * @return
     */
    protected abstract P createPresenter();

    /**
     * init view
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * layout id
     * @return
     */
    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
