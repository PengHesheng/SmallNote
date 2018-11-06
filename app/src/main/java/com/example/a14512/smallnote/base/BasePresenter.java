package com.example.a14512.smallnote.base;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * @author 14512 on 2018/11/1
 */
public abstract class BasePresenter<M extends IBaseMode, V extends IBaseView> {
    private Reference<V> mViewReference;
    protected V mView;
    protected M mMode;

    public BasePresenter(V view) {
        mViewReference = new SoftReference<>(view);
        mView = mViewReference.get();
        mMode = initMode();
    }

    /**
     * init mode
     * @return mode
     */
    protected abstract M initMode();

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
        }
    }
}
